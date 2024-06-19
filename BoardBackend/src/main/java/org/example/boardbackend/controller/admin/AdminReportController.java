package org.example.boardbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoardReport;
import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.example.boardbackend.service.board.dept.DeptBoardService;
import org.example.boardbackend.service.board.free.FreeBoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.controller.admin
 * fileName : AdminReportController
 * author : hayj6
 * date : 2024-06-14(014)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14(014)         hayj6          최초 생성
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/report")
public class AdminReportController {

    private final FreeBoardService freeBoardService;
    private final DeptBoardService deptBoardService;

    //    todo: 자유게시판 신고 글 조회
    @GetMapping("/free")
    public ResponseEntity<Object> findFreeReport(
            @RequestParam(defaultValue = "") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<FreeBoardReport> freeBoardReports
                    = freeBoardService.findFreeBoardReportsByUserIdContaining(userId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("freeBoardReportsList", freeBoardReports.getContent());       // faq 배열
            response.put("currentPage", freeBoardReports.getNumber());       // 현재페이지번호
            response.put("totalItems", freeBoardReports.getTotalElements()); // 총건수(개수)
            response.put("totalPages", freeBoardReports.getTotalPages());    // 총페이지수

            if (freeBoardReports.isEmpty() == false) {
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

    //    todo: 부서게시판 신고 글 조회
    @GetMapping("/dept")
    public ResponseEntity<Object> findDeptReport(
            @RequestParam(defaultValue = "") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<DeptBoardReport> deptBoardReports
                    = deptBoardService.findDeptBoardReportsByUserIdContaining(userId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("deptBoardReportsList", deptBoardReports.getContent());       // faq 배열
            response.put("currentPage", deptBoardReports.getNumber());       // 현재페이지번호
            response.put("totalItems", deptBoardReports.getTotalElements()); // 총건수(개수)
            response.put("totalPages", deptBoardReports.getTotalPages());    // 총페이지수

            if (deptBoardReports.isEmpty() == false) {
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

    //    todo 관리자 자유 : 신고게시글 삭제버튼
    @DeleteMapping("/free/deletion/{freeBoardId}")
    public ResponseEntity<Void> deleteFreeBoard(@PathVariable Long freeBoardId) {
        try {
            freeBoardService.deleteByFreeBoardId(freeBoardId);
            freeBoardService.updateByReportId(freeBoardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            log.debug(e.getMessage());
            return (ResponseEntity<Void>) ResponseEntity.internalServerError();
        }
    }

    //    todo 관리자 부서 : 신고게시글 삭제버튼
    @DeleteMapping("/dept/deletion/{deptBoardId}")
    public ResponseEntity<Void> deleteDeptBoard(@PathVariable Long deptBoardId) {
        try {
            deptBoardService.deleteByDeptBoardId(deptBoardId);
            deptBoardService.updateByDeptReportId(deptBoardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            log.debug(e.getMessage());
            return (ResponseEntity<Void>) ResponseEntity.internalServerError();
        }
    }

//    //    todo 관리자 신고게시글 삭제버튼
//    @DeleteMapping("/free/update/{reportId}")
//    public ResponseEntity<Void> deleteFreeBoard(@PathVariable Long reportId, @RequestBody FreeBoardReport freeBoardReport) {
//        try {
//            freeBoardService.updateByReportId(reportId);
//            return ResponseEntity.noContent().build();
//        } catch (Exception e){
//            log.debug(e.getMessage());
//            return (ResponseEntity<Void>) ResponseEntity.internalServerError();
//        }
//    }

    //    todo: 자유게시판 신고 처리완료 글 조회
    @GetMapping("/free/processed")
    public ResponseEntity<Object> findFreeReportIsProcessed(
            @RequestParam(defaultValue = "") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<FreeBoardReport> freeBoardReportsProcessed
                    = freeBoardService.findFreeBoardReportsByUserIdAndIsProcessed(userId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("freeBoardReportsListProcessed", freeBoardReportsProcessed.getContent());       // faq 배열
            response.put("currentPage", freeBoardReportsProcessed.getNumber());       // 현재페이지번호
            response.put("totalItems", freeBoardReportsProcessed.getTotalElements()); // 총건수(개수)
            response.put("totalPages", freeBoardReportsProcessed.getTotalPages());    // 총페이지수

            if (freeBoardReportsProcessed.isEmpty() == false) {
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

    //    todo: 부서게시판 신고 처리완료 글 조회
    @GetMapping("/dept/processed")
    public ResponseEntity<Object> findDeptReportIsProcessed(
            @RequestParam(defaultValue = "") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<DeptBoardReport> deptBoardReportsProcessed
                    = deptBoardService.findDeptBoardReportsByUserIdAndIsProcessed(userId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("deptBoardReportsListProcessed", deptBoardReportsProcessed.getContent());       // faq 배열
            response.put("currentPage", deptBoardReportsProcessed.getNumber());       // 현재페이지번호
            response.put("totalItems", deptBoardReportsProcessed.getTotalElements()); // 총건수(개수)
            response.put("totalPages", deptBoardReportsProcessed.getTotalPages());    // 총페이지수

            if (deptBoardReportsProcessed.isEmpty() == false) {
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

    //    todo 관리자 자유 : 신고게시글 취소 버튼
    @PutMapping("/free/update/{freeBoardId}")
    public ResponseEntity<Void> updateFreeBoard(@PathVariable Long freeBoardId) {
        try {
            log.debug("하유 정 ㅠ바보");
            freeBoardService.updateByReportId(freeBoardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            log.debug(e.getMessage());
            return (ResponseEntity<Void>) ResponseEntity.internalServerError();
        }
    }

    //    todo 관리자 부서 : 신고게시글 취소 버튼
    @PutMapping("/dept/update/{freeBoardId}")
    public ResponseEntity<Void> updateDeptBoard(@PathVariable Long deptBoardId) {
        try {
            log.debug("하유 정 ㅠ바보");
            deptBoardService.updateByDeptReportId(deptBoardId);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            log.debug(e.getMessage());
            return (ResponseEntity<Void>) ResponseEntity.internalServerError();
        }
    }
}
