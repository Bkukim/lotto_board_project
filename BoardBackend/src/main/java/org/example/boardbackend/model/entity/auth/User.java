package org.example.boardbackend.model.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.boardbackend.model.common.BaseTimeEntity;
import org.example.boardbackend.model.common.BaseTimeEntity2;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * packageName : org.example.boardbackend.model.entity.auth
 * fileName : User
 * author : hayj6
 * date : 2024-05-21(021)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21(021)         hayj6          최초 생성
 */
@Entity
@Table(name="LOTTO_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// soft delete jpa 어노테이션
@Where(clause = "WITHDRAW_YN = 'N'")
@SQLDelete(sql ="UPDATE LOTTO_USER SET WITHDRAW_YN = 'Y', WITHDRAW_TIME=TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE USER_ID = ?")
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "USER_ID")
    private String userId;
    private String password;
    private String userName;
    private long birthday;
    private String phoneNum;
    private String email;
    private String role;
    private String deptId;
    private String normalAddress;
    private String detailAddress;
    private String pwQuestion;
    private String pwAnswer;
    @Column(name = "WITHDRAW_YN")
    private String withdrawYN;

    public User(String userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public User(String userId, String password, String userName, long birthday, String phoneNum, String email, String role, String deptId, String normalAddress, String detailAddress, String withdrawYN) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = role;
        this.deptId = deptId;
        this.normalAddress = normalAddress;
        this.detailAddress = detailAddress;
        this.withdrawYN = withdrawYN;
    }
}
