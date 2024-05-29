package org.example.boardbackend.controller.normal.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.service.notice.NoticeRedisService;
import org.example.boardbackend.service.notice.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.normal.notice
 * fileName : NoticeController
 * author : PC
 * date : 2024-05-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22         PC          최초 생성
 */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/normal/notice")
public class NormalNoticeController {

    private final NoticeRedisService noticeRedisService;
//todo: 공지사항 clob이 string 변환이 안되어서
//      문제 되어서 조회가 안되는거라고 합니다...
//      아직 못찾았어요 그래서 타이틀만 조회하려고 하니까 레디스 어캐 조회하는지 몰라서 일단 둬요..ㅠ
    @GetMapping("/all/{eventYn}")
    public ResponseEntity<Object> getAllNotice(@PathVariable String eventYn, // 이벤트 가 존재하면 true 보내게
                                               @RequestParam String title,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        try {
            log.debug("실행시작");
            Pageable pageable = PageRequest.of(page, size);
            Map<String, Object> response = new HashMap<>();
            Page<INoticeDto> notices;
            if (eventYn.equals("y")) { // 이벤트가 있을경우 레디스 사용
                notices = noticeRedisService
                        .redisFindByTitleContaining(title, pageable);
            } else {
                notices = noticeRedisService
                        .findByTitleContaining(title, pageable);
            }
            response.put("notices", notices.getContent());
            response.put("currentPage", notices.getNumber());
            response.put("totalItems", notices.getTotalElements());
            response.put("totalPages", notices.getTotalPages());
            log.debug("실행다됨"+response);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{noticeId}/{eventYn}")
    public ResponseEntity<Object> getNoticeByNoticeId(@PathVariable long noticeId,
                                                      @PathVariable String eventYn) {
        try {
            Optional<Notice> notice;
            if (eventYn.equals("y")) {
                notice = noticeRedisService.redisFindById(noticeId);
            } else {
                log.debug("asdfasdf");
                notice = noticeRedisService.findById(noticeId);
            }
            return new ResponseEntity<>(notice.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
