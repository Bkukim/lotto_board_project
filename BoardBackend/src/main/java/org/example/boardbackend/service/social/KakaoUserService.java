package org.example.boardbackend.service.social;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.auth.SocialUserReq;
import org.example.boardbackend.model.dto.auth.UserRes;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.repository.user.UserRepository;
import org.example.boardbackend.security.jwt.JwtUtils;
import org.example.boardbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * packageName : org.example.routtoproject.service.member
 * fileName : KakaoUserService
 * author : PC
 * date : 2024-05-24
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-24         PC          최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoUserService implements SocialLoginService {

    // 속성에 입력한 카카오톡
    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    // 의존성 주입
    private  JwtUtils jwtUtils;
    private  UserService userService;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private HashMap <String, User> userHashMap = new HashMap<>();

    //    todo 카카오 로그인
    @Override
    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        // todo 1) 엑세스 토큰 가져오기
        String url = "https://kauth.kakao.com/oauth/token";
        // 바디생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);
        params.add("scope", "account_email");

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 생성 : 바디와 헤더를 넣어 요청으로 보냄
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);


        // 토큰 꺼내 사용하기
        Map<String, Object> responseBody = response.getBody();
        if (responseBody == null || !responseBody.containsKey("access_token")) {
            throw new RuntimeException("Failed to retrieve access token");
        }
        String accessToken = (String) responseBody.get("access_token");
        return accessToken;
    }

    @Override
    public UserRes getUserInfo(String accessToken) {
        // todo 2) 사용자 정보를 가져오기.
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        // 헤더 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + accessToken);
        // 요청 생성 : 헤더
        HttpEntity<String> entity = new HttpEntity<>(headers2);
        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);

        // 받은 정보 사용하기
        Map<String, Object> userInfo = userInfoResponse.getBody();
        if (userInfo == null) {
            throw new RuntimeException("Failed to retrieve user info");
        }

        String userId = ((Map<String, Object>) userInfo.get("kakao_account")).get("email").toString();

        if (userService.existsById(userId)) {
            // 프론트로 userRes 객체 보내기
            String jwt = jwtUtils.generateJwtTokenForKakao(userId);
            UserRes userRes = new UserRes(
                    jwt,                // 웹토큰
                    userId,
                    "ROLE_USER");
            return userRes;
        }else{
            String uuid = UUID.randomUUID().toString().replace("-", "");
            User user = new User();
            user.setUserId(userId);
            userHashMap.put(uuid,user);
            UserRes userRes = new UserRes(
                    null,                // 웹토큰
                    uuid,
                    null);
            return userRes;
        }
    }

    @Override
    @Transactional
    public UserRes socialRegister(String uuid, SocialUserReq socialUserReq) throws Exception {
        String userId = userHashMap.get(uuid).getUserId();
        User user = new User(userId,
                passwordEncoder.encode("asdfasdgfsadgqawrg23463457"),
                socialUserReq.getUserName(),
                socialUserReq.getBirthday(),
                socialUserReq.getPhoneNum(),
                userId,
                socialUserReq.getRole(),
                socialUserReq.getDeptId(),
                socialUserReq.getNormalAddress(),
                socialUserReq.getDetailAddress(),
                "N");
        userService.save(user);
        userHashMap.remove(uuid);
        String jwt = jwtUtils.generateJwtTokenForKakao(userId);
        UserRes userRes = new UserRes(
                jwt,                // 웹토큰
                userId,
                socialUserReq.getRole());
        return userRes;
    }
}