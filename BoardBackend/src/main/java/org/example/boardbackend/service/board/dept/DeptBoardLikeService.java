package org.example.boardbackend.service.board.dept;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.dept.DeptBoard;
import org.example.boardbackend.model.entity.board.dept.DeptBoardLike;
import org.example.boardbackend.repository.board.dept.DeptBoardLikeRepository;
import org.example.boardbackend.repository.board.dept.DeptBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * packageName : org.example.boardbackend.service.board.dept
 * fileName : FreeBoardLikeService
 * author : KimDJ
 * date : 2024-06-05
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-05         KimDJ          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptBoardLikeService {
    private final DeptBoardRepository deptBoardRepository;
    private final DeptBoardLikeRepository deptBoardLikeRepository;

    @Transactional
    public DeptBoardLike createDeptBoardLike(DeptBoardLike deptBoardLike) {
        // 이미 같은 FreeBoardId, userId 조합이 존재하는지 확인
        if (deptBoardLikeRepository.existsByDeptBoardIdAndUserId(deptBoardLike.getDeptBoardId(), deptBoardLike.getUserId())) {
            throw new RuntimeException("Already liked by this user");
        }

    // deptBoard likes 컬럼을 +1
    // deptBoardLike 테이블에 있는 id 가 동일한 게시글 찾아서 Likes +1 해줌
        DeptBoard deptBoard = deptBoardRepository.findById(deptBoardLike.getDeptBoardId())
                .orElseThrow(() -> new RuntimeException("deptBoard not found"));
        deptBoard.increaseOneDeptLikes(); // 좋아용 한개 증가 함수
        deptBoardRepository.save(deptBoard);

        return deptBoardLikeRepository.save(deptBoardLike);
    }

    // TODO: 좋아요 상태 확인 함수(좋아요 있음?)
    public DeptBoardLike getLikeByUserIdAndDeptBoardId(String userId, long deptBoardId) {
        return deptBoardLikeRepository.findByUserIdAndDeptBoardId(userId, deptBoardId);
    }

    // TODO: 좋아요 삭제 함수
    @Transactional
    public void deleteDeptBoardLike(Long likeId) {
        DeptBoardLike deptBoardLike = deptBoardLikeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        // FreeBoard의 likes 컬럼을 -1
        DeptBoard deptBoard = deptBoardRepository.findById(deptBoardLike.getDeptBoardId())
                .orElseThrow(() -> new RuntimeException("deptBoard not found"));

        deptBoard.decreaseOneDeptLikes();
        deptBoardRepository.save(deptBoard);
        deptBoardLikeRepository.delete(deptBoardLike);
    }

    // TODO: 좋아요 ID 조회 함수
    public Optional<Long> findLikeIdByUserIdAndDeptBoardId(String userId, Long deptBoardId) {
        Optional<DeptBoardLike> optionalDeptBoardLike = deptBoardLikeRepository.findByUserIdAndDeptBoardId(userId, deptBoardId);
        return optionalDeptBoardLike.map(DeptBoardLike::getLikeId);
    }

}
