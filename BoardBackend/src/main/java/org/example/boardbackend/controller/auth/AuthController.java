package org.example.boardbackend.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.auth.NewUser;
import org.example.boardbackend.model.dto.auth.SocialUserReq;
import org.example.boardbackend.model.dto.auth.UserReq;
import org.example.boardbackend.model.dto.auth.UserRes;
import org.example.boardbackend.model.dto.member.FindId;
import org.example.boardbackend.model.dto.member.NewPw;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.security.jwt.JwtUtils;
import org.example.boardbackend.service.social.KakaoUserService;
import org.example.boardbackend.service.social.SocialLoginService;
import org.example.boardbackend.service.user.EmailService;
import org.example.boardbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * packageName : org.example.board_login_in_webtoken.controller.auth
 * fileName : AuthController
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
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SocialLoginService kakaoUserService;

    //  todo 카카오 로그인
    @PostMapping("/kakao-login/{code}")
    public ResponseEntity<Object> kakaoLogin(@PathVariable String code){
        try {
            String accessToken = kakaoUserService.getAccessToken(code);
            UserRes userRes = kakaoUserService.getUserInfo(accessToken);
                return new ResponseEntity<>(userRes,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 카카오 회원가입 함수
    @PostMapping("/kakao-register/{uuid}")
    public ResponseEntity<Object> kakaoLogin(@PathVariable String uuid,
                                             @RequestBody SocialUserReq socialUserReq){
        try {

                UserRes RegisteredUserRes = kakaoUserService.socialRegister(uuid, socialUserReq);
              return new ResponseEntity<>(RegisteredUserRes, HttpStatus.OK);

        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>("회원가입 실패",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    todo 로그인 함수 : 로그인은 조회. URL에 안뜨게 하려고 GET아니고 POST
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody UserReq userReq      //  프론트에서 받은 정보
    ) {
        try {
//            1) 직접 인증 시작
//            1) id/패스워드로 인증 => 인증된 객체(리턴)
            Authentication authentication /*todo 여기서 어떻게 인증을 해주지 데이터베이스와 연결이 돼있나? 혹시 doFilterInternal 로?*/
                    = authenticationManagerBuilder.getObject().authenticate(
                    new UsernamePasswordAuthenticationToken(userReq.getUserId(), userReq.getPassword())
//                    DB인증 클래스까지 실행시킴.(참고). 그래서 DB인증이 된다
            );

//            2) 인증된 객체 => 홀더(필통)에 관리 => 인증 완료 : 필통에 인증된 객체를 꽃아서 프론트로 전달
            SecurityContextHolder.getContext().setAuthentication(authentication);

//            3) 웹토큰 발행(생성) => 프론트 전송
//               => authentication => memberDto => email 로 웹토큰 생성
            String jwt = jwtUtils.generateJwtToken(authentication);

//            4) 권한 정보 : ROLE_USER, ROLE_ADMIN
//              => Set -> List => new ArrayList(Set객체) : 변경됨
//              => authentication.getAuthorities() : 권한 배열 집합(set)
//            권한 클래스 : GRANTED로 되어있는데 그걸 문자열로 바꿔주기. 자바스크립트에서 사용하기 위해 문자열로 변환.
            String role = new ArrayList(authentication.getAuthorities()).get(0).toString();

////            todo 상세조회 : nullPointError 방지. 있으면 user 객체에 넣는다.
//            User user = userService.findById(userReq.getUserId())
//                    .orElseThrow(()->new UsernameNotFoundException("유저가 없습니다."));

//             5) DTO : jwt(웹토큰), 이메일, 권한명 => 프론트에 전송
            UserRes userRes = new UserRes(
                    jwt,                // 웹토큰
                    userReq.getUserId(), // 이메일
                    role

            );
            return new ResponseEntity<>(userRes, HttpStatus.OK); // 따로따로 못보내니까 USERRES라는 객체를 써서 안에 속성적어서 보냄

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    todo 회원 가입 함수(insert)
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(
            @RequestBody NewUser newUser
    ) {
        try {
//            1) 이메일이 DB 있는지 확인 : 있으면 우리 회원 => 바로 리턴
            log.debug(String.valueOf(newUser));
            if (userService.existsById(newUser.getUserId()) == true) {
                return new ResponseEntity<>("이미 회원입니다.", HttpStatus.BAD_REQUEST);
            }

//            2) 없으면 새로운 사용자 생성
            User user = new User(
                    newUser.getUserId(),                            // 로그인 ID
                    passwordEncoder.encode(newUser.getPassword()), // 암호화 적용
                    newUser.getUserName(),                             // 이름
                    newUser.getBirthday(),
                    newUser.getPhoneNum(),
                    newUser.getEmail(),
                    newUser.getRole(),
                    newUser.getDepartment(),
                    newUser.getNormalAddress(),
                    newUser.getDetailAddress(),
                    newUser.getPwQuestion(),
                    newUser.getPwAnswer(),
                    "N"
            );
            log.debug(String.valueOf(user));
//            3) 저장
            userService.save(user);

//            4) 프론트 전송
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
//            log.debug("에러 : " + e); // 디버깅
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo id 찾기함수
    @GetMapping("/findId/{role}/{userName}/{phoneNum}")
    public ResponseEntity<Object> findId(@PathVariable String role,
                                         @PathVariable String userName,
                                         @PathVariable String phoneNum){
        try {
            User user = userService.findId(role, userName, phoneNum);
            FindId findId = new FindId("존재하지 않는 회원입니다");
            if (user == null) {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo 비밀번호 찾기위해 회원확인함수 - 확인후 일치하면 true를 내보낸다.
    @GetMapping("/getForPw/{role}/{userId}/{pwQuestion}/{pwAnswer}")
    public ResponseEntity<Object> getForPw(@PathVariable String role,
                                           @PathVariable String userId,
                                           @PathVariable String pwQuestion,
                                           @PathVariable String pwAnswer){
        try {
            boolean user = userService.getForPw(role, userId, pwQuestion, pwAnswer);
            if (user) {

                return new ResponseEntity<>(true, HttpStatus.OK);
            }else {

                return new ResponseEntity<>(false,HttpStatus.OK);
            }
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // todo 새로운 비밀번호 업데이트
    @PutMapping("/new-pw/{userId}")
    public ResponseEntity<Object> updatePw(@PathVariable String userId,
                                           @RequestBody NewPw newPw){
        boolean result = false;
        try {
            if (userService.existsById(newPw.getUserId())) {
                userService.updatePw(passwordEncoder.encode(newPw.getNewPw()), newPw.getUserId());
                result=true;
                return new  ResponseEntity<>(result,HttpStatus.OK);
            }else {

                return new ResponseEntity<>(result,HttpStatus.OK);
            }

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // todo id 존재 여부 확인
    @GetMapping("/exist-id/{userId}")
    public ResponseEntity<Object> existUserById(@PathVariable String userId){
        try {
            log.debug("디버그 ::: "+userId);
            boolean result = userService.existsById(userId);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private final EmailService emailService;
    // ?는 requestParam, ?이하는 변수를 알아서 받음 / 그냥은 PathVariable, {}가 들어감
    @PutMapping("/sendEmail")
    public ResponseEntity<Object> sendEmail(@RequestParam String email, @RequestParam String userId){
        if (userService.existsByUserIdAndEmail(userId,email)) {
            log.debug("이메일"+email);
            log.debug("아이디"+userId);
            emailService.sendSimpleEmail(email, userId);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.OK);
        }

    }
// todo userid와 email로 회원 검사
    @GetMapping("/exist/email")
    public ResponseEntity<Object> checkByUserIdAndEmail(@RequestParam String email, @RequestParam String userId){
        boolean result = userService.existsByUserIdAndEmail(userId,email);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/exist-pw")
    public ResponseEntity<Object> checkByPw(@RequestBody UserReq userReq){
        boolean result = userService.confirmByPw(userReq);
        return ResponseEntity.ok(result);
    }
}
