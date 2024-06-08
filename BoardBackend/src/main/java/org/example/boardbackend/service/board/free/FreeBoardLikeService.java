package org.example.boardbackend.service.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.DuplicateResourceException;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.FreeBoardLike;
import org.example.boardbackend.repository.board.free.FreeBoardLikeRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;


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

    //    TODO: 추가
//    @Transactional
//    public void insert(FreeBoardLikeDto freeBoardLikeDto) throws Exception {
////todo: 레포짓토리인지 객체파일인지 가져올때 잘보고 바꿀것
//        User user = userRepository.findById(freeBoardLikeDto.getUserId())
//                .orElseThrow(() -> new NotFoundException("Could not found member id : " + freeBoardLikeDto.getUserId()));
//
//        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardLikeDto.getFreeBoardId())
//                .orElseThrow(() -> new NotFoundException("Could not found board id : " + freeBoardLikeDto.getUserId()));
//
//        // 이미 좋아요되어있으면 에러 반환
//        if (freeBoardLikeRepository.findByUserIdAndFreeBoardId(user, freeBoard).isPresent()) {
//            //TODO 409에러로 변경
//            throw new DuplicateResourceException("already exist data by member id :" + user.getUserId() + " ,"
//                    + "board id : " + freeBoard.getFreeBoardId());
//        }
//
//        FreeBoardLike freeBoardLike = FreeBoardLike.builder()
//                .freeBoard(freeBoard)
//                .user(user)
//                .build();
//
//        freeBoardLikeRepository.save(freeBoardLike);
////        freeBoardRepository.addLikeCount(board);
//    }
//
//    //    todo : 삭제
//    @Transactional
//    public void delete(FreeBoardLikeDto freeBoardLikeDto) {
//
//        User user = userRepository.findById(freeBoardLikeDto.getUserId())
//                .orElseThrow(() -> new NotFoundException("Could not found member id : " + freeBoardLikeDto.getUserId()));
//
//        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardLikeDto.getFreeBoardId())
//                .orElseThrow(() -> new NotFoundException("Could not found board id : " + freeBoardLikeDto.getFreeBoardId()));
//
//        FreeBoardLike freeBoardLike = freeBoardLikeRepository.findByUserIdAndFreeBoardId(user, freeBoard)
//                .orElseThrow(() -> new NotFoundException("Could not found heart id"));
//
//        freeBoardLikeRepository.delete(freeBoardLike);
////    boardRepository.subLikeCount(board);
//    }


    // 예외처리 해주는 클래스
    public class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
