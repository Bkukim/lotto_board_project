package org.example.boardbackend.service.board.dept;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.dept.DeptBoardDto;
import org.example.boardbackend.model.dto.board.dept.DeptRecommentDto;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.example.boardbackend.model.entity.board.dept.DeptBoardReport;
import org.example.boardbackend.model.entity.board.dept.DeptComment;
import org.example.boardbackend.model.entity.board.dept.DeptRecomment;
import org.example.boardbackend.model.entity.board.free.FreeBoardReport;
import org.example.boardbackend.repository.board.dept.DeptBoardRepository;
import org.example.boardbackend.repository.board.dept.DeptCommentRepository;
import org.example.boardbackend.repository.board.dept.DeptRecommentRepository;
import org.example.boardbackend.repository.board.dept.DeptBoardReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.free
 * fileName : FreeBoardService
 * author : hayj6
 * date : 2024-05-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27(027)         hayj6          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptBoardService {

    private final DeptBoardRepository deptBoardRepository;
    private final DeptCommentRepository deptCommentRepository;
    private final DeptRecommentRepository deptRecommentRepository;
    private final DeptBoardReportRepository deptBoardReportRepository;

    //    todo 전체 조회
    public Page<DeptBoardDto> selectByTitleContaining(
            String title,
            String deptId,
            Pageable pageable
    ) {
        Page<DeptBoardDto> page
                = deptBoardRepository.findAllByTitleContaining(
                title,
                deptId,
                pageable
        );
        return page;
    }

    //   todo 상세조회
    public Optional<DeptBoard> findById(long freeBoardId) {
//        DB 상세조회 실행
        Optional<DeptBoard> freeBoardOptional
                = deptBoardRepository.findById(freeBoardId);
        return freeBoardOptional;
    }

    //   todo:  저장 함수
    @Transactional
    public void save(DeptBoard deptBoard) throws IOException {
       deptBoardRepository.save(deptBoard);
    }

    // todo: 삭제
    @Transactional
    public boolean removeById(long deptBoardId) throws IOException{
        if (deptBoardRepository.existsById(deptBoardId)) {
            deptBoardRepository.deleteById(deptBoardId);
            return true;
        } else {
            return false;
        }
    }
    // TODO 댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    @Transactional
    public void saveComment(DeptComment deptComment) {
        DeptBoard deptBoard = deptBoardRepository.findById(deptComment.getDeptBoardId()).get();

        // 1. 댓글 저장
        deptCommentRepository.save(deptComment);


    }

    // TODO 대댓글 저장 기능
    // 1. boardId로 게시글 주인의 객체 가져오기,  1. 댓글을 저장, 2 알림 보내기
    @Transactional
    public void saveRecomment(DeptRecomment deptRecomment){

        // 1. 댓글 저장
        deptRecommentRepository.save(deptRecomment);


    }
    //    todo: boardId 로 댓글 조회 함수
    public Page<DeptComment> getCommentByDeptBoardId(long deptBoardId, Pageable pageable) throws IOException{
        Page<DeptComment> deptComments = deptCommentRepository.findDeptCommentsByDeptBoardIdOrderByInsertTimeDesc(deptBoardId, pageable);
        return deptComments;
    }
    //    todo: boardId 로 대댓글 조회 함수
    public List<DeptRecommentDto> getRecommentByDeptBoardId(long deptBoardId) throws IOException{
        return deptRecommentRepository.findDeptBoardRecommentsByDeptBoardCommentIdOrderByInsertTimeDesc(deptBoardId);
    }

    // todo : 댓글id 로 댓글 상세조회
    public DeptComment findByCommentId(long deptCommentId) throws IOException{
        DeptComment comment = deptCommentRepository.findById(deptCommentId).get();
        return comment;
    }

    // todo : 신고 저장함수
    @Transactional
    public void saveReport(DeptBoardReport deptBoardReport){
        deptBoardReportRepository.save(deptBoardReport);
    }

    //    todo : userId가 작성한 글 전체조회
    public Page<DeptBoardDto> findDeptBoardByUserIdContaining(String userId, Pageable pageable)
    {
        Page<DeptBoardDto> page
                = deptBoardRepository.findDeptBoardByUserIdContaining(userId, pageable);
        return page;
    }

    //   todo : 관리자 : 신고 게시판 조회
    public Page<DeptBoardReport> findDeptBoardReportsByUserIdContaining(String userId, Pageable pageable){

        Page<DeptBoardReport> reports = deptBoardReportRepository.findDeptBoardReportsByUserIdContaining(userId, pageable);

        return reports;
    }

    //    todo 신고게시글 삭제버튼 누를시 실행될 함수 : 부서게시판은 삭제
    public void deleteByDeptBoardId(long deptBoardId) {
        log.debug("tkdyd?");
//        freeBoardReportRepository.updateByFreeBoardId(freeBoardId);
        deptBoardRepository.deleteById(deptBoardId);
    }

    //    todo 신고게시글 삭제버튼 누를시 실행될 함수 : 신고테이블에서는 Y로 변경
    public void updateByDeptReportId(long deptBoardId) {
        log.debug("tkdyd?");
        deptBoardReportRepository.updateByDeptBoardId(deptBoardId);
//        freeBoardRepository.deleteById(reportId);
    }

    //   todo : 관리자 : 신고 게시판 처리완료 조회
    public Page<DeptBoardReport> findDeptBoardReportsByUserIdAndIsProcessed(String userId, Pageable pageable) {

        Page<DeptBoardReport> reports = deptBoardReportRepository.findDeptBoardReportsByUserIdAndIsProcessed(userId, pageable);

        return reports;
    }
}
