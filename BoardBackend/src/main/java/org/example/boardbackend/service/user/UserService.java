package org.example.boardbackend.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.auth.UserReq;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName : org.example.board_login_in_webtoken.service.auth
 * fileName : UserService
 * author : hayj6
 * date : 2024-05-22(022)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22(022)         hayj6          최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //    todo : 관리자 : userName으로 회원 조회
    public Page<User> findUserByUserNameContaining(String userName, Pageable pageable) {
        Page<User> page
                = userRepository.findByUserName(userName, pageable);
        return page;
    }

    //    todo 회원 있는지 확인 함수 : 회원가입
    public boolean existsById(String userId) {
        boolean result = userRepository.existsById(userId);

        return result;
    }

    //    todo 저장 : 회원가입
    public User save(User user) {
        User user2 = userRepository.save(user);

        return user2;
    }

    //    TODO: userID 상세 조회
    public Optional<User> findById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    // todo : id찾는 함수
    public User findId(String role, String userName, String phoneNum) {
        User user = userRepository.findUserByRoleAndUserNameAndPhoneNum(role, userName, phoneNum);
        return user;
    }

    // todo 비밀번호 찾기위해 회원 확인 함수
    public boolean getForPw(String role, String userId, String pwQuestion, String pwAnswer) {
        User user = userRepository.getForPw(role, userId, pwQuestion, pwAnswer);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    // todo 새로운 비밀번호 update함수
    public void updatePw(String newPw, String userId) {

        userRepository.updatePw(newPw, userId);
    }

    //    todo 회원정보 수정 함수
    public void updateUserById(String role, String userName
            , long birthday
            , String phoneNum
            , String email
            , String deptId
            , String normalAddress
            , String detailAddress
            , String userId) {
        userRepository.updateUserById(role, userName
                , birthday
                , phoneNum
                , email
                , deptId
                , normalAddress
                , detailAddress
                , userId);
    }

    // todo 유저 삭제 함수 (소프트 삭제)
    @Transactional
    public boolean removeById(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    // todo 비밀번호 확인 함수
    @Transactional
    public boolean confirmByPw(UserReq userReq) {
        try {
            Authentication authentication /*todo 여기서 어떻게 인증을 해주지 데이터베이스와 연결이 돼있나? 혹시 doFilterInternal 로?*/
                    = authenticationManagerBuilder.getObject().authenticate(
                    new UsernamePasswordAuthenticationToken(userReq.getUserId(), userReq.getPassword())
//                    DB인증 클래스까지 실행시킴.(참고). 그래서 DB인증이 된다
            );
            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            return false;
        }

    }
    // todo userId와 email로 확인
    @Transactional
    public boolean existsByUserIdAndEmail(String userId, String email) {
       boolean result = userRepository.existsByUserIdAndEmail(userId,email);
       return result;
    }
}
