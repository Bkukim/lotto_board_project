package org.example.boardbackend.model.dto.board.free;

import lombok.*;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardLikeDto
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
@Setter
@Getter
@ToString
//생성자 어노테이션
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardLikeDto {
    //    like dto, 회원id, 게시펀id get
    private String userId;
    private long freeBoardId;

//    public FreeBoardLikeDto(String userId, long freeBoardId){
//        this.userId=userId;
//        this.freeBoardId=freeBoardId;
//    }

}
