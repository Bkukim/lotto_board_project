package org.example.boardbackend.repository.notice;

import org.example.boardbackend.model.dto.notice.INoticeDto;
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
public interface NoticeRepository extends JpaRepository<Notice,Long> {
    @Query(value = "SELECT NOTICE_ID AS noticeId\n" +
            ", TITLE AS title\n" +
            ", CONTENT AS content\n" +
            ", NOTICE_ING_URL AS noticeImgUrl\n" +
            ", NOTICE_IMG_UUID AS noticeImgUuid\n" +
            ", EVENT_YN AS eventYn\n" +
            "FROM NOTICE\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true
    )
    Page<INoticeDto> findByTitleContaining(@Param("title") String title, Pageable pageable);
}
