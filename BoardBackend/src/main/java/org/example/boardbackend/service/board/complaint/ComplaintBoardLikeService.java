package org.example.boardbackend.service.board.complaint;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoardLike;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardLikeRepository;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardRepository;
import org.example.boardbackend.repository.board.free.FreeBoardLikeRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * packageName : org.example.boardbackend.service.board.free
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
//
// @RequiredArgsConstructor는 Lombok의 어노테이션으로,
// 클래스의 final 필드와 @NonNull 필드를 초기화할 수 있는 생성자를 자동으로 생성
// 이를 통해 보일러플레이트 코드를 줄이고, 코드의 가독성과 유지 보수성을 높일 수 있습니다.
// ** 보일러 플레이트 코드란: 최소한의 변경으로 여러곳에서 재사용되며, 반복적으로 비슷한 형태를 띄는 코드를 말한다.
// ** @NonNull : 필드, 메서드 매개변수, 또는 메서드 반환 값이 null이 될 수 없음을 나타냅니다.
//               이 어노테이션을 사용하면 해당 값이 null일 경우 자동으로 예외를 발생시킵니다.
@RequiredArgsConstructor
public class ComplaintBoardLikeService {
    private final ComplaintBoardLikeRepository complaintBoardLikeRepository;
    private final ComplaintBoardRepository complaintBoardRepository;

    @Transactional
    public ComplaintBoardLike createComplaintBoardLike(ComplaintBoardLike complaintBoardLike) {
        // 이미 같은 complaintBoardId, userId 조합이 존재하는지 확인
        if (complaintBoardLikeRepository.existsByComplaintBoardIdAndUserId(complaintBoardLike.getComplaintBoardId(), complaintBoardLike.getUserId())) {
            throw new RuntimeException("Already liked by this user");
        }

    // likes 컬럼을 +1
    // ComplaintLike 테이블에 있는 id 가 동일한 게시글 찾아서 Likes +1 해줌
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardLike.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("ComplaintBoardId not found"));
        complaintBoard.setLikes(complaintBoard.getLikes() + 1);
        complaintBoardRepository.save(complaintBoard);

        return complaintBoardLikeRepository.save(complaintBoardLike);

    }

    // TODO: 좋아요 상태 확인 함수(좋아요 있음?)
    public ComplaintBoardLike getLikeByUserIdAndComplaintBoardId(String userId, long complaintBoardId) {
        return complaintBoardLikeRepository.findByUserIdAndComplaintBoardId(userId, complaintBoardId);
    }

    // TODO: 좋아요 삭제 함수
    @Transactional
    public void deleteComplaintBoardLike(Long likeId) {
        ComplaintBoardLike complaintBoardLike = complaintBoardLikeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        // ComplaintBoardId likes 컬럼을 -1
        ComplaintBoard complaintBoard = complaintBoardRepository.findById(complaintBoardLike.getComplaintBoardId())
                .orElseThrow(() -> new RuntimeException("ComplaintBoardId not found"));

        complaintBoard.setLikes(complaintBoard.getLikes() - 1);
        complaintBoardRepository.save(complaintBoard);
        complaintBoardLikeRepository.delete(complaintBoardLike);
    }

    // TODO: 좋아요 ID 조회 함수
    public Optional<Long> findLikeIdByUserIdAndComplaintBoardId(String userId, Long complaintBoardId) {
        Optional<ComplaintBoardLike> optionalComplaintBoardLike = complaintBoardLikeRepository.findByUserIdAndComplaintBoardId(userId, complaintBoardId);
        return optionalComplaintBoardLike.map(ComplaintBoardLike::getLikeId);
    }

}
