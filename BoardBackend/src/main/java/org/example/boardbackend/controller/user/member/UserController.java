package org.example.boardbackend.controller.user.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.repositorytest.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.member
 * fileName : UserController
 * author : hayj6
 * date : 2024-05-28(028)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28(028)         hayj6          최초 생성
 */
@Slf4j
@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    todo : userId 상세 조회
    @GetMapping("{userId}")
    public ResponseEntity<Object> findByUserID(@PathVariable String userId){
        try {
            Optional<User> user = userService.findById(userId);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
