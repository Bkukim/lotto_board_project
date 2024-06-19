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
            "WHERE TITLE LIKE '%'|| :title ||'%'" +
            "order by insert_Time desc"
            , countQuery = "SELECT COUNT(*)\n" +
            "FROM LOTTO_NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true
    )
    Page<INoticeDto> findByTitleContaining(@Param("title") String title, Pageable pageable);

//todo: [전체]
    @Query(value = "SELECT NOTICE_ID AS noticeId\n" +
            ", TITLE AS title" +
            ", VIEWS AS views" +
            ", INSERT_TIME AS insertTime" +
            ", NOTICE_TYPE AS noticeType\n" +
            "FROM LOTTO_NOTICE\n" +
            " WHERE NOTICE_TYPE = '전체'" +
            "order by insert_Time desc",
            nativeQuery = true)
    List<INoticeDto> findByNoticeTypeAll(INoticeDto iNoticeDto);
// todo: 부서게시판 조회

    @Query(value = "SELECT NOTICE_ID AS noticeId\n" +
            ", TITLE AS title" +
            ", VIEWS AS views" +
            ", INSERT_TIME AS insertTime" +
            ", NOTICE_TYPE AS noticeType\n" +
            "FROM LOTTO_NOTICE\n" +
            " WHERE NOTICE_TYPE = '부서'" +
            "order by insert_Time desc",
            nativeQuery = true)
    List<INoticeDto> findByNoticeTypeDept(INoticeDto iNoticeDto);

    // todo: 자유게시판 조회

@Query(value = "SELECT NOTICE_ID AS noticeId\n" +
        ", TITLE AS title" +
        ", VIEWS AS views" +
        ", INSERT_TIME AS insertTime" +
        ", NOTICE_TYPE AS noticeType\n" +
        "FROM LOTTO_NOTICE\n" +
        " WHERE NOTICE_TYPE = '자유'" +
        "order by insert_Time desc",
        nativeQuery = true)
List<INoticeDto> findByNoticeTypeFree(INoticeDto iNoticeDto);

// todo: 건의게시판 조회
@Query(value = "SELECT NOTICE_ID AS noticeId\n" +
        ", TITLE AS title" +
        ", VIEWS AS views" +
        ", INSERT_TIME AS insertTime" +
        ", NOTICE_TYPE AS noticeType\n" +
        "FROM LOTTO_NOTICE\n" +
        " WHERE NOTICE_TYPE = '건의'" +
        "order by insert_Time desc",
        nativeQuery = true)
List<INoticeDto> findByNoticeTypeComplaint(INoticeDto iNoticeDto);

// todo: 동호회 게시판 조회
@Query(value = "SELECT NOTICE_ID AS noticeId\n" +
        ", TITLE AS title" +
        ", VIEWS AS views" +
        ", INSERT_TIME AS insertTime" +
        ", NOTICE_TYPE AS noticeType\n" +
        "FROM LOTTO_NOTICE\n" +
        " WHERE NOTICE_TYPE = '동호회'" +
        "order by insert_Time desc",
        nativeQuery = true)
List<INoticeDto> findByNoticeTypeGroup(INoticeDto iNoticeDto);


}
