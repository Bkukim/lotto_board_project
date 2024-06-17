package org.example.boardbackend.service.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.club.Participants;
import org.example.boardbackend.repository.board.club.ParticipantsRepository;
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

//  TODO: 신청 승인 함수
    @Transactional
    public Participants approveParticipant(long clubBoardId, String userId) {
        Participants participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        participant.setApproval("Y");
        return participantsRepository.save(participant);
    }

//  TODO: 승인하기 :: 승인취소 버튼
    @Transactional
    public void toggleApproval(long clubBoardId, String userId) {
        Participants participant = participantsRepository.findByClubBoardIdAndUserId(clubBoardId, userId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        if ("N".equals(participant.getApproval())) {
            participant.setApproval("Y");
        } else {
            participant.setApproval("N");
        }
        participantsRepository.save(participant);
    }
}
