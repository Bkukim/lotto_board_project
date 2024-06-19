package org.example.boardbackend.repository.board.complaint;

import org.example.boardbackend.model.dto.board.complaint.ComplaintBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.complaint
 * fileName : ComplaintBoardRepository
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Repository
public interface ComplaintBoardRepository extends JpaRepository<ComplaintBoard, Long> {

//    todo : title로 검색
    @Query(value = "SELECT COMPLAINT_BOARD_ID AS complaintBoardId, TITLE AS title, USER_ID AS userId, INSERT_TIME AS insertTime, LIKES AS likes, STATUS AS status FROM LOTTO_COMPLAINT_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"+
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count(*)FROM LOTTO_COMPLAINT_BOARD\n" +
            "WHERE TITLE LIKE '%'|| :title ||'%'"
            , nativeQuery = true)
    Page<ComplaintBoardDto> findComplaintBoardByTitleContaining(@Param("title") String title,
                                                                Pageable pageable
    );


    //    todo userId가 작성한 글 보기
    @Query(value = "SELECT COMPLAINT_BOARD_ID AS complaintBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_COMPLAINT_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"+
            "ORDER BY INSERT_TIME DESC"
            ,countQuery = "SELECT count (*) FROM LOTTO_COMPLAINT_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            ,nativeQuery = true)
    Page<ComplaintBoardDto> findComplaintBoardByUserIdContaining(@Param("userId") String userId,
                                                       Pageable pageable);
}
