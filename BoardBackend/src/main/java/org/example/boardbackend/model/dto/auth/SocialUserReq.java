package org.example.boardbackend.model.dto.auth;

import lombok.*;

/**
 * packageName : org.example.boardbackend.model.dto.auth
 * fileName : SocialUserReq
 * author : hayj6
 * date : 2024-06-07(007)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-07(007)         hayj6          최초 생성
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserReq {
    private String userName;
    private long birthday;
    private String phoneNum;
    private String email;
    private String role;
    private String deptId;
    private String normalAddress;
    private String detailAddress;
    private String pwUpdateTime;
}
