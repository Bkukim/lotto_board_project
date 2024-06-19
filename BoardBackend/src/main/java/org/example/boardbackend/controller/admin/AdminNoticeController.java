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
import java.util.Optional;

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

    //    todo: 조회 4개 있음 master 사용 dept, free, group, complaint
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

    //  Todo: type
    @GetMapping("/master-all")
    public ResponseEntity<Object> findAllType(INoticeDto iNoticeDto) {
        try {
            // 전체 조회 서비스 실행
            List<INoticeDto> noticeAll = noticeService.findByNoticeTypeAll(iNoticeDto);

            Map<String, Object> response = new HashMap<>();
//            todo: 맵 <키, 벨류> 둘중 하나가 틀렸는지 꼭 확인할 것
            response.put("noticeAll", noticeAll);
            response.put("totalItems", noticeAll.size()); // 총건수(개수)

            if (!noticeAll.isEmpty()) {
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

    @GetMapping("/master-free")
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
    @GetMapping("/master-complaint")
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

    @GetMapping("/master-group")
    public ResponseEntity<Object> findGroup(INoticeDto iNoticeDto) {
        try {
            // 전체 조회 서비스 실행
            List<INoticeDto> noticeGroup = noticeService.findByNoticeTypeGroup(iNoticeDto);

            Map<String, Object> response = new HashMap<>();
            response.put("noticeGroup", noticeGroup);
            response.put("totalItems", noticeGroup.size()); // 총건수(개수)

            if (!noticeGroup.isEmpty()) {
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

    // todo: 관리자 권한 상세조회
// todo: 관리자 권한 상세조회
//    todo: 상세조회 만들기
//    조회(select) -> get 방식 -> @GetMapping
    @GetMapping("/{noticeId}")
    public ResponseEntity<Object> findById(
            @PathVariable int noticeId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<Notice> noticeOptional
                    = noticeService.findById(noticeId);

            if (noticeOptional.isEmpty() == true) {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
//                조회 성공
                return new ResponseEntity<>(noticeOptional.get(), HttpStatus.OK);

            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //todo: 수정함수
    @PutMapping("/notice-update/{noticeId}")
    public ResponseEntity<Object> update(
            @PathVariable long noticeId
            , @RequestBody Notice notice
    ) {
        noticeService.save(notice);
        return new ResponseEntity<>(HttpStatus.OK);
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

    //    todo: 전체 함수
    @DeleteMapping("/delete-all")
    public ResponseEntity<Object> deleteAll() {
        try {
            boolean success = noticeService.removeAll();
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
