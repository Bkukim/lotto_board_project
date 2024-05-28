package org.example.boardbackend.repository.notice;

import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            ", VIEWS AS views\n" +
            ", INSERT_TIME AS insertTime\n" +
            "FROM LOTTO_NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , countQuery = "SELECT COUNT(*)\n" +
            "FROM LOTTO_NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true
    )
    Page<INoticeDto> findByTitleContaining(@Param("title") String title, Pageable pageable);
//    @Query(value = "SELECT NOTICE_ID, TITLE \n" +
//            "FROM LOTTO_NOTICE\n" +
//            "WHERE TITLE LIKE '%'||:title||'%'",
//    countQuery = "SELECT NOTICE_ID, TITLE \n" +
//            "FROM LOTTO_NOTICE\n" +
//            "WHERE TITLE LIKE '%h%'",
//    nativeQuery = true)
//    Page<NoticeAllDto> findAllByTitleContaining(@Param("title") String title, Pageable pageable);
}
