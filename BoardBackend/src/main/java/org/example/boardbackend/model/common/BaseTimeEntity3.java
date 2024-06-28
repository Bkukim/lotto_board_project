package org.example.boardbackend.model.common;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName : org.example.simpledms.model.common
 * fileName : BaseTimeEntity2
 * author : GGG
 * date : 2024-04-04
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04         GGG          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity3 {

    @Column(updatable = false)
    private String insertTime;

    private String updateTime;
    //    TODO: 삭제여부 필드 : Y, N (soft delete 용 == update 문 실행)
//     select * from tb_dept where delete_yn = 'N';
    @Column(name = "DELETE_YN")
    private String deleteYn;


    @PrePersist
    void OnPrePersist() {
//        this.insertTime = LocalDateTime.now()
//                .format(DateTimeFormatter
//                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 현재 한국 시간 가져오기
        ZonedDateTime koreaTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // 포맷 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 포맷에 맞춰 시간 출력
        this.insertTime = koreaTime.format(formatter);
    }

    @PreUpdate
    void OnPreUpdate() {
//        this.updateTime = LocalDateTime.now()
//                .format(DateTimeFormatter
//                        .ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 현재 한국 시간 가져오기
        ZonedDateTime koreaTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // 포맷 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 포맷에 맞춰 시간 출력
        this.insertTime = koreaTime.format(formatter);
    }
}

