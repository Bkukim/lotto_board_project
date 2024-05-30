package org.example.boardbackend.service.social.naver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.repository.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.service.social.naver
 * fileName : CustomOAuth2UserService
 * author : BALLBAT
 * date : 2024-05-29
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29         BALLBAT          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 네이버 OAuth2 공급자로부터 사용자 정보 가져오기
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 네이버의 경우 응답에서 필요한 정보를 `response` 필드에 담아서 제공함
        var attributes = oAuth2User.getAttributes();
        if (registrationId.equals("naver")) {
            attributes = (Map<String, Object>) attributes.get("response");
        }

        // 사용자 정보 저장 및 업데이트 로직
        String userId = (String) attributes.get("email");
        saveOrUpdate(userId);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                userNameAttributeName
        );
    }

    private void saveOrUpdate(String userId) {
        if (!userRepository.existsById(userId)) {
            User user = new User(
                    userId, "password", "ROLE_USER"
            );
            userRepository.save(user);
        }
    }
}
