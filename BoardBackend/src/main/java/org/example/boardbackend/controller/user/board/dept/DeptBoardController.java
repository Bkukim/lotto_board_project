package org.example.boardbackend.controller.user.board.dept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.constant.DeptCode;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.dto.board.dept.DeptRecommentDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.example.boardbackend.model.entity.board.dept.DeptComment;
import org.example.boardbackend.model.entity.board.dept.DeptRecomment;
import org.example.boardbackend.model.entity.dept.Department;
import org.example.boardbackend.service.board.dept.DeptBoardService;
import org.example.boardbackend.service.dept.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.board.dept
 * fileName : DeptBaordCopntroller
 * author : PC
 * date : 2024-06-06
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-06         PC          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/user/dept")
@RequiredArgsConstructor
public class DeptBoardController {

    private final DeptBoardService deptBoardService;
    private final DepartmentService departmentService;

    //    todo 전체 조회 + 제목 검색 + 페이징
    @GetMapping("/board")
    public ResponseEntity<Object> findAllDeptBoard(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "")String deptId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<DeptBoardDto> freeBoard
                    = deptBoardService
                    .selectByTitleContaining(title, deptId, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("deptBoardList", freeBoard.getContent());       // faq 배열
            response.put("currentPage", freeBoard.getNumber());       // 현재페이지번호
            response.put("totalItems", freeBoard.getTotalElements()); // 총건수(개수)
            response.put("totalPages", freeBoard.getTotalPages());    // 총페이지수

            if (freeBoard.isEmpty() == false) {
//                조회 성공
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
//                데이터 없음
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: 상세조회
    @GetMapping("/board/{deptBoardId}")
    public ResponseEntity<Object> findDeptBoardById(
            @PathVariable long deptBoardId
    ) {
        try {
//            상세조회 서비스 실행
            Optional<DeptBoard> deptBoard
                    = deptBoardService.findById(deptBoardId);
            if (deptBoard.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(deptBoard.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo: 저장함수
    @PostMapping("/board/save")
    public ResponseEntity<Object> createDeptBoard(
            @RequestBody DeptBoard deptBoard
    ) {
        try {
            log.debug(deptBoard.toString());
//            DB 서비스 저장 함수 실행
            deptBoardService.save(deptBoard);
//            성공(OK) 메세지 + 저장된객체
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 수정 함수 : 수정 버튼 클릭시 실행될 함수
    @PutMapping("/board/update/{deptBoardId}")
    public ResponseEntity<Object> update(
            @PathVariable long deptBoardId,
            @RequestBody DeptBoard deptBoard
    ) {
        try {
            log.debug(deptBoard.toString());
            deptBoardService.save(deptBoard);  // 수정
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 삭제 함수
    @DeleteMapping("/board/deletion/{deptBoardId}")
    public ResponseEntity<Object> delete(
            @PathVariable long deptBoardId
    ) {
        try {
//            DB 서비스 삭제 함수 실행
            boolean success = deptBoardService.removeById(deptBoardId);

            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // 삭제 실행 : 0건 삭제(삭제할 데이터 없음)
                String message = "존재하지 않는 데이터입니다.";
                return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
//            서버(DB) 에러
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 댓글 조회 함수
    @GetMapping("/comment")
    public ResponseEntity<Object> getCommentsByFreeBoardId(@RequestParam(defaultValue = "0") long deptBoardId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            Page<DeptComment> comments = deptBoardService.getCommentByDeptBoardId(deptBoardId, pageable);

            if (comments.isEmpty() == false) {
                Map<String, Object> response = new HashMap<>();

                response.put("deptBoardComments", comments.getContent());       // faq 배열
                response.put("currentPage", comments.getNumber());       // 현재페이지번호
                response.put("totalItems", comments.getTotalElements()); // 총건수(개수)
                response.put("totalPages", comments.getTotalPages());    // 총페이지수

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    TODO: 대댓글 조회 함수
    @GetMapping("/recomment/{deptBoardId}")
    public ResponseEntity<Object> getCommentsByFreeBoardCoId(@RequestParam(defaultValue = "0") long deptBoardId) {
        try {
            // 해당 freeBoardId에 대한 댓글을 가져오도록 서비스 메서드를 호출
            List<DeptRecommentDto> recomments = deptBoardService.getRecommentByDeptBoardId(deptBoardId);

            if (recomments.isEmpty() == false) {

                return new ResponseEntity<>(recomments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.debug("대댓글 디버그 :: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 댓글 저장 함수
    @PostMapping("/comment/save")
    public ResponseEntity<Object> saveFreeComment(@RequestBody DeptComment DeptComment) {
        try {
            //            DB 서비스 저장 함수 실행
            deptBoardService.saveComment(DeptComment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("디버그"+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 대댓글 저장 함수
    @PostMapping("/recomment/save")
    public ResponseEntity<Object> saveFreeRecomment(@RequestBody DeptRecomment deptRecomment) {
        log.debug("대댓글"+deptRecomment);

        try {
            deptBoardService.saveRecomment(deptRecomment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.debug("디버그 :: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 부서 전체조회
    @GetMapping("/department")
    public ResponseEntity<Object> findAllDepartment(){
        try {
            List<Department> departments = departmentService.findAllDepartment();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }

    // todo 부서 상세 조회
    @GetMapping("/department/{deptId}")
    public ResponseEntity<Object> findAllDepartment(@PathVariable String deptId){
        try {
            Department department = departmentService.findDepartmentByDeptId(deptId);
            return new ResponseEntity<>(department, HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }
}
