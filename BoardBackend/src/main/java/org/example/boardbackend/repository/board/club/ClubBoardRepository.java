package org.example.boardbackend.repository.board.club;

import org.example.boardbackend.model.dto.board.club.ClubBoardDto;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.repository.board
 * fileName : ClubBoardRepository
 * author : BALLBAT
 * date : 2024-05-23
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23         BALLBAT          최초 생성
 */
@Repository
public interface ClubBoardRepository extends JpaRepository<ClubBoard, Long> {
    @Query(value = "SELECT CB.CLUB_BOARD_ID as clubBoardId, CB.USER_ID as userId, CB.LIKES as likes, CB.CONTENT as content, " +
            "CB.LOCATION as location, CB.ADDRESS as address, CB.PARTICIPATION_FEE as participationFee, CB.START_TIME as startTime, " +
            "CB.END_TIME as endTime, CB.RECRUITMENT_DEADLINE as recruitmentDeadline, CB.MAX_QUOTA as maxQuota, CB.MIN_QUOTA as minQuota, " +
            "CB.PEOPLES_MATCH as peoplesMatch, CB.MATERIAL as material, CB.SEX as sex, CB.MATCH_FORM as matchForm, CB.TITLE as title, " +
            "FP.IMG_URL as imgUrl " +
            "FROM LOTTO_CLUB_BOARD CB " +
            "JOIN LOTTO_FIELD_PIC FP ON CB.CLUB_BOARD_ID = FP.CLUB_BOARD_ID " +
            "WHERE CB.CLUB_BOARD_ID = :clubBoardId", nativeQuery = true)
    List<Map<String, Object>> findClubBoardWithPics(@Param("clubBoardId") long clubBoardId);

    //  TODO: userId로 작성한 글 보기
    @Query(value = "SELECT CLUB_BOARD_ID AS clubBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_CLUB_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'" +
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count (*) FROM LOTTO_CLUB_BOARD\n" +
            "WHERE USER_ID LIKE '%'|| :userId ||'%'"
            , nativeQuery = true)
    Page<ClubBoardDto> findClubBoardByUserIdContaining(@Param("userId") String userId, Pageable pageable);

    //  TODO: 로 작성한 글 보기
    @Query(value = "SELECT CLUB_BOARD_ID AS clubBoardId, USER_ID AS userId, TITLE AS title, INSERT_TIME AS insertTime, LIKES AS likes FROM LOTTO_CLUB_BOARD\n" +
            "WHERE LOCATION LIKE '%'|| :location ||'%'" +
            "ORDER BY INSERT_TIME DESC"
            , countQuery = "SELECT count (*) FROM LOTTO_CLUB_BOARD\n" +
            "WHERE LOCATION LIKE '%'|| :location ||'%'"
            , nativeQuery = true)
    Page<ClubBoardDto> findClubBoardByLocationContaining(@Param("location") String location, Pageable pageable);


}
