package org.example.boardbackend.service.board.free;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.free.FreeBoardLikeDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.repository.board.free.FreeBoardLikeRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


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
@RequiredArgsConstructor
public class FreeBoardLikeService {
    private final UserRepository userRepository;
    private final FreeBoardRepository freeBoardRepository;
//    TODO: 좋아요 한번더 클릭하면 취소되게(여러번 누를 수 없게)
    private final FreeBoardLikeRepository freeBoardLikeRepository;

   @Transactional
//    public  void insert(FreeBoardLikeDto freeBoardLikeDto) throws  Exception{
//       User user = freeBoardRepository.findById(Long.valueOf(freeBoardLikeDto.getUserId()))
//               .orElseThrow(() -> new ChangeSetPersister.NotFoundException()
//
//       FreeBoard freeBoard = boardRepository.findById(heartRequestDTO.getBoardId())
//               .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartRequestDTO.getBoardId()));
//
//       // 이미 좋아요되어있으면 에러 반환
//       if (heartRepository.findByMemberAndBoard(member, board).isPresent()){
//           //TODO 409에러로 변경
//           throw new DuplicateResourceException("already exist data by member id :" + member.getId() + " ,"
//                   + "board id : " + board.getId());
//       }
//
//       Heart heart = Heart.builder()
//               .board(board)
//               .member(member)
//               .build();
//
//       heartRepository.save(heart);
//       boardRepository.addLikeCount(board);
//   }


    public class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }

        public NotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
