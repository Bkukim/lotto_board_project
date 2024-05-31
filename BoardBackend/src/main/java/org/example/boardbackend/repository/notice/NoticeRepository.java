package org.example.boardbackend.repository.notice;

import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.dto.notice.NoticeAllDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName : org.example.boardbackend.repository.event
 * fileName : EventRepository
 * author : PC
 * date : 2024-05-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21         PC          최초 생성
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "SELECT NOTICE_ID AS noticeId\n" +
            ", TITLE AS title\n" +
            ", NOTICE_TYPE AS noticeType\n" +
            ", VIEWS AS views\n" +
            ", EVENT_YN AS eventYn\n" +
            ", UPDATE_TIME AS updateTime\n" +
            ", INSERT_TIME AS insertTime\n" +
            "FROM LOTTO_NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , countQuery = "SELECT COUNT(*)\n" +
            "FROM LOTTO_NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true
    )
    Page<INoticeDto> findByTitleContaining(@Param("title") String title, Pageable pageable);

    // todo: 자유게시판 조회
    @Query(value = "SELECT NOTICE_ID AS noticeId\n" +
            ", TITLE AS title" +
            ", VIEWS AS views" +
            ", INSERT_TIME AS insertTime" +
            ", NOTICE_TYPE AS noticeType\n" +
            "FROM LOTTO_NOTICE\n" +
            " WHERE NOTICE_TYPE = '부서'",
            nativeQuery = true)
    List<INoticeDto> findByNoticeTypeDept(INoticeDto iNoticeDto);

// todo: 부서게시판 조회

// todo: 건의게시판 조회

// todo: 동호회 게시판 조회


}
