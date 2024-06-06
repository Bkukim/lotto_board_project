package org.example.boardbackend.repositorytest;

import org.example.boardbackend.repository.board.dept.DeptRecommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * packageName : org.example.boardbackend.repositorytest
 * fileName : DeptRepositoryTest
 * author : PC
 * date : 2024-06-05
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         PC          최초 생성
 */
@SpringBootTest
public class DeptRepositoryTest {

    @Autowired
    DeptRecommentRepository deptRecommentRepository;
    @Test
    void 부서_대댓글조회_함수() throws IOException {
        final long boardId = 2;
        String userId = deptRecommentRepository.findDeptBoardRecommentsByDeptBoardCommentIdOrderByInsertTimeDesc(boardId).get(0).getUserId();


        assertEquals("user12", userId);
    }

}
