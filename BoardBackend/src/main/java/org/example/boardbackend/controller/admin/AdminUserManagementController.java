package org.example.boardbackend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.controller.admin
 * fileName : AdminUserManagementController
 * author : hayj6
 * date : 2024-06-12(012)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-12(012)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
public class AdminUserManagementController {

    private final UserService userService;

    //    todo: 관리자 : UserName으로 회원 조회
    @GetMapping("/manage")
    public ResponseEntity<Object> findUserName(
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
//            페이징 객체 생성
            Pageable pageable = PageRequest.of(page, size);

//            전체 조회 서비스 실행
            Page<User> user
                    = userService.findUserByUserNameContaining(userName, pageable);

//            공통 페이징 객체 생성 : 자료구조 맵 사용
            Map<String, Object> response = new HashMap<>();
            response.put("userList", user.getContent());       // faq 배열
            response.put("currentPage", user.getNumber());       // 현재페이지번호
            response.put("totalItems", user.getTotalElements()); // 총건수(개수)
            response.put("totalPages", user.getTotalPages());    // 총페이지수

            if (user.isEmpty() == false) {
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

    // todo 관리자 : 회원 정보 수정 함수 : 회원정보수정
    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable String userId,
                                             @RequestBody User user){
        try {
            userService.updateUserById(user.getUserName(), user.getBirthday(),user.getPhoneNum(), user.getEmail(), user.getDeptId(), user.getNormalAddress(), user.getDetailAddress(), user.getUserId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 관리자 : 회원 소프트 삭제 함수 : 회원 탈퇴
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId){
        try {
            log.debug("디버그 " + userId);
            userService.removeById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
