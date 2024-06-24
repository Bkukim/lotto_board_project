package org.example.boardbackend.service.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.redis.MessageDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.Participants;
import org.example.boardbackend.model.entity.notify.Notify;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.example.boardbackend.repository.board.club.ParticipantsRepository;
import org.example.boardbackend.service.notify.NotifyService;
import org.example.boardbackend.service.redis.RedisMessageService;
import org.example.boardbackend.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.club
 * fileName : ParticipantsService
 * author : BALLBAT
 * date : 2024-06-14
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14         BALLBAT          최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ParticipantsService {
    private final ParticipantsRepository participantsRepository;
    private final ClubBoardRepository clubBoardRepository;
    private final NotifyService notifyService;

    //  TODO: 저장 함수
    @Transactional
    public Participants createParticipant(long clubBoardId, String userId) {
        Participants participant = Participants.builder()
                .clubBoardId(clubBoardId)
                .userId(userId)
                .approval("N")
                .build();
        return participantsRepository.save(participant);
    }

    public void sendNotification(long clubBoardId) {
        ClubBoard clubBoard = clubBoardRepository.findById(clubBoardId)
                .orElseThrow(() -> new RuntimeException("ClubBoard not found"));
        String boardWriter = clubBoard.getUserId();
        String notifyContent = "회원님의 게시물에 새로운 참가 신청이 있습니다.";
        String notifyUrl = "club/club-boardRecruitment/" + clubBoardId;
        MessageDto messageDto = new MessageDto(Notify.NotificationType.CLUB_APPLICATION,notifyContent,boardWriter,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }

    //  TODO: 삭제 함수
    @Transactional
    public void deleteParticipant(long clubBoardId, String userId) {
        Participants participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        participantsRepository.delete(participant);
    }

    //  TODO: 신청 상태 확인 함수
    @Transactional
    public boolean checkParticipant(long clubBoardId, String userId) {
        Optional<Participants> participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId);
        return participant.isPresent();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public boolean isParticipantExist(long clubBoardId, String userId) {
        return participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId).isPresent();
    }

    //  TODO: 작성자 확인해서 신청 현황 띄우는 함수
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Participants> getParticipantsByClubBoardId(long clubBoardId) {
        return participantsRepository.findAllByClubBoardId(clubBoardId);
    }

    // TODO: 신청 승인 함수
//    @Transactional
//    public Participants approveParticipant(long clubBoardId, String userId) {
//        Participants participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId)
//                .orElseThrow(() -> new RuntimeException("Participant not found"));
//        participant.setApproval("Y");
//        return participantsRepository.save(participant);
//    }

    // TODO: 승인 상태 변경 함수
    @Transactional
    public void approveParticipant(long clubBoardId, String userId) {
        Participants participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        if ("N".equals(participant.getApproval())) {
            participant.setApproval("Y");
        } else {
            participant.setApproval("N");
        }
        participantsRepository.save(participant);
    }

    // 승인 알림 전송 함수
    public void sendNotificationForApproval(long clubBoardId, String userId) {
        String notifyContent = "회원님의 참가 신청이 승인되었습니다.";
        String notifyUrl = "club/club-boardRecruitment/" + clubBoardId;
        MessageDto messageDto = new MessageDto(Notify.NotificationType.CLUB_APPROVAL,notifyContent,userId,notifyUrl);
        notifyService.publishNotificationToRedis(messageDto);
    }
}
