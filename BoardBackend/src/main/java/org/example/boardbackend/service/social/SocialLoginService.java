package org.example.boardbackend.service.social;

import org.example.boardbackend.model.dto.auth.SocialUserReq;
import org.example.boardbackend.model.dto.auth.UserRes;

import java.io.IOException;

/**
 * packageName : org.example.routtoproject.service.member.social
 * fileName : SocialLoginService
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

public interface SocialLoginService {

    // todo 인가코드로 엑세스 토큰 발급
    public String getAccessToken(String code);

    // todo 액세스 토큰으로 유저정보 가져오는 함수
    public UserRes getUserInfo(String accessToken);
    public UserRes socialRegister(String userId, SocialUserReq socialUserReq) throws IOException;
}
