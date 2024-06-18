package org.example.boardbackend.controller.user.board.club;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.club.Participants;
import org.example.boardbackend.service.board.club.ParticipantsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.controller.user.board.club
 * fileName : ParticipantsController
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
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/club-board")
public class ParticipantsController {
    private final ParticipantsService participantsService;

//  TODO: 생성 함수
    @PostMapping("/participants")
    public ResponseEntity<Participants> createParticipant(@RequestParam long clubBoardId, @RequestParam String userId) {
        Participants participant = participantsService.createParticipant(clubBoardId, userId);
        return ResponseEntity.ok(participant);
    }

//  TODO: 삭제 함수
@DeleteMapping("/participants/deletion")
public ResponseEntity<Void> deleteParticipant(@RequestParam long clubBoardId, @RequestParam String userId) {
    participantsService.deleteParticipant(clubBoardId, userId);
    return ResponseEntity.noContent().build();
}

//  TODO: 신청 상태 확인 함수
@GetMapping("/participants/check")
public ResponseEntity<Map<String, Boolean>> checkParticipant(@RequestParam long clubBoardId, @RequestParam String userId) {
    boolean isApplied = participantsService.checkParticipant(clubBoardId, userId);
    Map<String, Boolean> response = new HashMap<>();
    response.put("isApplied", isApplied);
    return ResponseEntity.ok(response);
}

//  TODO: 작성자 확인해서 신청 현황 띄우는 함수
    @GetMapping("/participants/{clubBoardId}")
    public ResponseEntity<List<Participants>> getParticipants(@PathVariable long clubBoardId) {
        List<Participants> participants = participantsService.getParticipantsByClubBoardId(clubBoardId);
        return ResponseEntity.ok(participants);
    }

//  TODO: 신청 승인 함수
    @PutMapping("/participants/approve")
    public ResponseEntity<Void> approveParticipant(@RequestParam long clubBoardId, @RequestParam String userId) {
        participantsService.approveParticipant(clubBoardId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/participants/toggle-approval")
    public ResponseEntity<?> toggleApproval(@RequestParam long clubBoardId, @RequestParam String userId) {
        try {
            participantsService.toggleApproval(clubBoardId, userId);
            return ResponseEntity.ok().body("승인 상태가 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("승인 상태 변경 실패");
        }
    }
}
