package org.example.boardbackend.controller.user.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.complaint.ComplaintBoardDto;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardComment;
import org.example.boardbackend.repositorytest.board.complaint.ComplaintBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.board.complaint
 * fileName : ComplaintBoardController
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/user/board")
@RequiredArgsConstructor
public class ComplaintBoardController {

    private final ComplaintBoardService complaintBoardService;

    //    todo 전체 조회 + 제목 검색 + 페이징
    @GetMapping("/complaint")
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<ComplaintBoardDto> complaintBoard
                    = complaintBoardService
                    .findComplaintBoardByTitleContaining(title, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("complaintBoardList", complaintBoard.getContent());       // faq 배열
            response.put("currentPage", complaintBoard.getNumber());       // 현재페이지번호
            response.put("totalItems", complaintBoard.getTotalElements()); // 총건수(개수)
            response.put("totalPages", complaintBoard.getTotalPages());    // 총페이지수

            if (complaintBoard.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: 상세조회
    @GetMapping("/complaint/{complaintBoardId}")
    public ResponseEntity<Object> findById(
            @PathVariable long complaintBoardId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<ComplaintBoard> complaintBoardOptional
                    = complaintBoardService.findById(complaintBoardId);
            if (complaintBoardOptional.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(complaintBoardOptional.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo:  저장함수
    @PostMapping("/complaint/save")
    public ResponseEntity<Object> createFreeBoard(
            @RequestBody ComplaintBoard complaintBoard
    ) {
        try {
//            DB 서비스 저장 함수 실행
            ComplaintBoard complaintBoard1 = complaintBoardService.save(complaintBoard);
            log.debug("디버그"+complaintBoard1.toString());
//            성공(OK) 메세지 + 저장된객체
            return new ResponseEntity<>(complaintBoard1, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정 함수 : 수정 버튼 클릭시 실행될 함수
    @PutMapping("/complaint/update/{complaintBoardId}")
    public ResponseEntity<Object> update(
            @PathVariable long complaintBoardId,
            @RequestBody ComplaintBoard complaintBoard
    ) {
        try {
            ComplaintBoard complaintBoard1 = complaintBoardService.save(complaintBoard);  // 수정
            return new ResponseEntity<>(complaintBoard1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 삭제 함수
    @DeleteMapping("/complaint/deletion/{complaintBoardId}")
    public ResponseEntity<Object> delete(
            @PathVariable long complaintBoardId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = complaintBoardService.removeById(complaintBoardId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버(DB) 에러
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 댓글 조회 함수
    @GetMapping("/complaint/{complaintBoardId}/comments")
    public ResponseEntity<Object> getCommentsByFreeBoardId(@PathVariable long complaintBoardId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);


            // 해당 freeBoardId에 대한 댓글을 가져오도록 서비스 메서드를 호출
            Page<ComplaintBoardComment> comments = complaintBoardService.getCommentByComplaintBoardId(complaintBoardId, pageable);

            if (comments.isEmpty() == false) {
                // 공통 페이징 객체 생성 : 자료구조 맵 사용
                Map<String, Object> response = new HashMap<>();

                response.put("complaintBoardComments", comments.getContent());       // faq 배열
                response.put("currentPage", comments.getNumber());       // 현재페이지번호
                response.put("totalItems", comments.getTotalElements()); // 총건수(개수)
                response.put("totalPages", comments.getTotalPages());    // 총페이지수

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 댓글 저장 함수
    @PostMapping("/complaint/save/comment")
    public ResponseEntity<Object> saveComplaintComment(@RequestBody ComplaintBoardComment complaintBoardComment) {
        try {
            //            DB 서비스 저장 함수 실행
            complaintBoardService.saveComment(complaintBoardComment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("asdfasdf"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
