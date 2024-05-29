package org.example.boardbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.service.notice.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
