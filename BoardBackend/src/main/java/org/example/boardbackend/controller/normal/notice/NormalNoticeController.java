package org.example.boardbackend.controller.normal.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.dto.notice.NoticeAllDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.repository.notice.NoticeRepository;
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
import java.util.List;
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
    private  final  NoticeService noticeService;

    //todo: 공지사항 clob이 string 변환 => entity에 컬럼추가해 주니 됨 @Column(name = "CONTENT")
    @GetMapping("/all")
    public ResponseEntity<Object> getAllNotice(/*@RequestParam String eventYn, // 이벤트 가 존재하면 true 보내게*/
                                               @RequestParam String title,
                                               @RequestParam int page,
                                               @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Map<String, Object> response = new HashMap<>();
            Page<INoticeDto> notices;
//            if (eventYn.equals("Y")) { // 이벤트가 있을경우 레디스 사용
                notices = noticeRedisService
                        .findByTitleContaining(title, pageable);
//            } else {
//                notices = noticeRedisService
//                        .findByTitleContaining(title, pageable);
//            }
            response.put("notices", notices.getContent());
            log.debug("asdfasdf"+notices.getContent());
            log.debug("페이지와 사이즈"+page+size);
            response.put("currentPage", notices.getNumber());
            response.put("totalItems", notices.getTotalElements());
            response.put("totalPages", notices.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("공지사항 전체 " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/{noticeId}")
    public ResponseEntity<Object> getNoticeByNoticeId(@PathVariable long noticeId,
                                                      @RequestParam String eventYN) {
        try {
            Optional<Notice> notice;
            if (eventYN.equals("Y")) {
                notice = noticeRedisService.redisFindById(noticeId);
            } else {
                notice = noticeRedisService.findById(noticeId);
            }
            return new ResponseEntity<>(notice, HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    //    todo: 상세조회 만들기
////    조회(select) -> get 방식 -> @GetMapping
//    @GetMapping("/{noticeId}")
//    public ResponseEntity<Object> findById(
//            @PathVariable int noticeId
//    ) {
//        log.debug("컨트롤러1");
//        try {
////            상세조회 서비스 실행
//            Optional<Notice> noticeOptional
//                    = noticeRedisService.findById(noticeId);
//
//            if (noticeOptional.isEmpty() == true) {
////                데이터 없음
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
////                조회 성공
//                return new ResponseEntity<>(noticeOptional.get(), HttpStatus.OK);
//
//            }
//        } catch (Exception e) {
//            log.debug("컨트롤러2");
//
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //    todo: 조회수 update
    @PutMapping("/notice-update-views/{noticeId}")
    public ResponseEntity<Object> update(
            @PathVariable long noticeId
            , @RequestBody Notice notice
    ) {
        try {
//            notice.setViews(notice.getViews() + 1);
            noticeRedisService.save(notice);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
// todo: main 조회
    @GetMapping("/main-get")
    public ResponseEntity<Object> findMainNotice(NoticeAllDto noticeAllDto) {
        try {
            // 전체 조회 서비스 실행
log.debug("컨트롤성공");
            List<NoticeAllDto> notice = noticeService.selectByMainNotice(noticeAllDto);

            Map<String, Object> response = new HashMap<>();
            response.put("notice", notice);
//            response.put("totalItems", noticeGroup.size()); // 총건수(개수)

            if (!notice.isEmpty()) {
                // 조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // 데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("컨트롤실패");
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//  TODO: 각 게시판에서 조회하기

    //    todo: 조회 4개 있음 master 사용 dept, free, group, complaint
    @GetMapping("/notice-dept")
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

    //  Todo: type
//    @GetMapping("/notice-all")
//    public ResponseEntity<Object> findAllType(INoticeDto iNoticeDto) {
//        try {
//            // 전체 조회 서비스 실행
//            List<INoticeDto> noticeAll = noticeService.findByNoticeTypeAll(iNoticeDto);
//
//            Map<String, Object> response = new HashMap<>();
////            todo: 맵 <키, 벨류> 둘중 하나가 틀렸는지 꼭 확인할 것
//            response.put("noticeAll", noticeAll);
//            response.put("totalItems", noticeAll.size()); // 총건수(개수)
//
//            if (!noticeAll.isEmpty()) {
//                // 조회 성공
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                // 데이터 없음
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/notice-free")
    public ResponseEntity<Object> findFree(INoticeDto iNoticeDto) {
        try {
            // 전체 조회 서비스 실행
            List<INoticeDto> noticeFree = noticeService.findByNoticeTypeFree(iNoticeDto);

            Map<String, Object> response = new HashMap<>();
            response.put("noticeFree", noticeFree);
            response.put("totalItems", noticeFree.size()); // 총건수(개수)

            if (!noticeFree.isEmpty()) {
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

    //  Todo: 건의
    @GetMapping("/notice-complaint")
    public ResponseEntity<Object> findComplaint(INoticeDto iNoticeDto) {
        try {
            // 전체 조회 서비스 실행
            List<INoticeDto> noticeComplaint = noticeService.findByNoticeTypeComplaint(iNoticeDto);

            Map<String, Object> response = new HashMap<>();
            response.put("noticeComplaint", noticeComplaint);
            response.put("totalItems", noticeComplaint.size()); // 총건수(개수)

            if (!noticeComplaint.isEmpty()) {
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
}
