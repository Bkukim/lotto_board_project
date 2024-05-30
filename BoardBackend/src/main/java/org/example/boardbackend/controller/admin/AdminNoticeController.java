package org.example.boardbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.service.notice.NoticeService;
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
 * fileName : AdminNoticeController
 * author : KimDJ
 * date : 2024-05-23
 * description : 관리자 공지사항 등록 등
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23         KimDJ          최초 생성
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/notice")
public class AdminNoticeController {
    private final NoticeService noticeService;

//    todo: dept, free, group, complaint

    @GetMapping("/master-dept")
    public ResponseEntity<Object> findDept(INoticeDto iNoticeDto) {
        try {
            // 전체 조회 서비스 실행
            List<INoticeDto> noticeDept = noticeService.findByNoticeTypeDept(iNoticeDto);

            Map<String, Object> response = new HashMap<>();
            response.put("noticeDept", noticeDept);
            response.put("totalItems", noticeDept.size()); // 총건수(개수)

            if (!noticeDept.isEmpty()) {
                // 조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //   todo: 저장함수 editor
    @PostMapping("/notice-add")
    public ResponseEntity<Object> createNotice(
            @RequestBody Notice notice
    ) {
        try {
            Notice notice1 = noticeService.save(notice);
            return new ResponseEntity<>(notice1, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
//    todo: 삭제함수
@DeleteMapping("/notice-deletion/{noticeId}")
public ResponseEntity<Object> delete(
        @PathVariable long noticeId
) {
    try {
        boolean success = noticeService.removeById(noticeId);

        if (success == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
