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
 * fileName : BaseTimeEntity
 * author : GGG
 * date : 2024-04-02
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02         GGG          최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(updatable = false)
    private String insertTime;

    private String updateTime;

    //    TODO: JPA 에서 insert 가 실행되기전에 실행하는 함수
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

    //    TODO: JPA 에서 update 가 실행되기전에 실행되는 함수
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

