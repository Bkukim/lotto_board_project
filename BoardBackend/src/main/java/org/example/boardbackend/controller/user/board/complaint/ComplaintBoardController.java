package org.example.boardbackend.controller.user.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.service.board.complaint.ComplaintBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
            Page<ComplaintBoard> complaintBoard
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
}
