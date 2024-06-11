package org.example.boardbackend.repository.board.free;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.model.entity.board.free.QFreeBoard;
import org.springframework.stereotype.Repository;

/**
 * packageName : org.example.boardbackend.repository.board.free
 * fileName : FreeBoardLikeRepositoryImpl ->FreeBoardLikeRepository2이렇게 변경함 custom 파일을 쓰지 않을거라서
 * author : KimDJ
 * date : 2024-06-10
 * description : 좋아요기능 ::게시글에 대한 좋아요 count + 1 (중복 좋아요 불가)
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-10         KimDJ          최초 생성
 */
@RequiredArgsConstructor
@Repository
public class FreeBoardLikeRepository2 {
    private final JPAQueryFactory jpaQueryFactory;
//    QFreeBoardLike qFreeBoardLike;
    QFreeBoard qFreeBoard;
    public void updateCount(FreeBoard freeBoard, boolean b) {
        if (b) {
            jpaQueryFactory.update(qFreeBoard.freeBoard)
                    .set(qFreeBoard.likes, qFreeBoard.likes.add(1))
                    .where(qFreeBoard.eq(freeBoard))
                    .execute();
        } else {
            jpaQueryFactory.update(qFreeBoard)
                    .set(qFreeBoard.likes, qFreeBoard.likes.subtract(1))
                    .where(qFreeBoard.eq(freeBoard))
                    .execute();
        }
    }

}
