package org.example.boardbackend.service.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.DuplicateResourceException;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.repository.board.free.FreeBoardLikeRepository;
import org.example.boardbackend.repository.board.free.FreeBoardLikeRepository2;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;


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
public class FreeBoardLikeService {
    private final UserRepository userRepository;
    private final FreeBoardRepository freeBoardRepository;
    //    TODO: 좋아요 한번더 클릭하면 취소되게(여러번 누를 수 없게)
    private final FreeBoardLikeRepository freeBoardLikeRepository;
    private final FreeBoardLikeRepository2 freeBoardLikeRepository2;

//    chat
//@Transactional
//public void saveFreeBoardLike(String userId, Long freeBoardId) throws Exception {
//    User user = userRepository.findById(userId)
//            .orElseThrow(() -> new Exception("User not found with id: " + userId));
//    FreeBoard freeBoard = freeBoardRepository.findById(freeBoardId)
//            .orElseThrow(() -> new Exception("FreeBoard not found with id: " + freeBoardId));
//
//    FreeBoardLike freeBoardLike = FreeBoardLike.builder()
//            .userId(user)
//            .freeBoardId(freeBoard)
//            .build();
//
//    freeBoardLikeRepository.save(freeBoardLike);
//}

    //todo: 해당 유저 like 조회
    public List<FreeBoardLikeDto> findByNoticeTypeDept(String userId, long freeBoardId) {
        List<FreeBoardLikeDto> freeBoardLikeDto2 = freeBoardLikeRepository.findAllByUserIdContainingAndFreeBoardId(freeBoardId);
        return freeBoardLikeDto2;
    }


    // todo: 저장
    public FreeBoardLike save(FreeBoardLike freeBoardLike) {
        FreeBoardLike freeBoardLike1 = freeBoardLikeRepository.save(freeBoardLike);
        return freeBoardLike1;
    }

    // todo: 수정
    public FreeBoardLike update(FreeBoardLike freeBoardLike) {
        FreeBoardLike freeBoardLike1 = freeBoardLikeRepository.save(freeBoardLike);
        return freeBoardLike1;
    }

    // todo: 식제
    public boolean removeById(long likeId) {

        if (freeBoardLikeRepository.existsById(likeId)) {
            freeBoardLikeRepository.deleteById(likeId);
            return true;
        }
        return false;
    }


// todo: 예제 방식으로 해본것, 복잡해서 선생님 방식으로 변경하기
//    //    TODO: 추가
//    @Transactional
//    public void insert(FreeBoardLikeDto freeBoardLikeDto) throws Exception {
//        User user = userRepository.findById(freeBoardLikeDto.getUserId())
//                .orElseThrow(() -> new NotFoundException("Could not found user id : " + freeBoardLikeDto.getUserId()));
////
//        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardLikeDto.getFreeBoardId())
//                .orElseThrow(() -> new NotFoundException("Could not found free board id : " + freeBoardLikeDto.getUserId()));
//
//        // 이미 좋아요되어있으면 에러 반환
//        if (freeBoardLikeRepository.findByUserAndFreeBoard(user, freeBoard).isPresent()) {
//            //TODO 409에러로 변경
//            throw new DuplicateResourceException("already exist data by user id :" + user.getUserId() + " ,"
//                    + "free board id : " + freeBoard.getFreeBoardId());
//        }
//
//        FreeBoardLike freeBoardLike = FreeBoardLike.builder()
//                .freeBoard(freeBoard)
//                .user(user)
//                .build();
//
//        freeBoardLikeRepository.save(freeBoardLike);
//        freeBoardLikeRepository2.updateCount(freeBoard,true);
//    }
//    //
////        todo : 삭제
//    @Transactional
//    public void delete(FreeBoardLikeDto freeBoardLikeDto) {
//
//        User user = userRepository.findById(freeBoardLikeDto.getUserId())
//                .orElseThrow(() -> new NotFoundException("Could not found user id : " + freeBoardLikeDto.getUserId()));
//
//        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardLikeDto.getFreeBoardId())
//                .orElseThrow(() -> new NotFoundException("Could not found free board id : " + freeBoardLikeDto.getFreeBoardId()));
//
//        FreeBoardLike freeBoardLike = freeBoardLikeRepository.findByUserAndFreeBoard(user, freeBoard)
//                .orElseThrow(() -> new NotFoundException("Could not found like id"));
//
//        freeBoardLikeRepository.delete(freeBoardLike);
//        freeBoardLikeRepository2.updateCount(freeBoard,false);
//
////    boardRepository.subLikeCount(board);
//    }
//
//
//    // 예외처리 해주는 클래스
//    public class NotFoundException extends RuntimeException {
//
//        public NotFoundException(String message) {
//            super(message);
//        }
//
//        public NotFoundException(String message, Throwable cause) {
//            super(message, cause);
//        }
//    }
}
