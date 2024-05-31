package org.example.boardbackend.model.dto.board.free;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.boardbackend.model.entity.auth.User;

/**
 * packageName : org.example.boardbackend.model.dto.board.free
 * fileName : FreeBoardDto
 * author : hayj6
 * date : 2024-05-31(031)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-31(031)         hayj6          최초 생성
 */

public interface FreeBoardDto {
// 1. 인터페이스 Dto : nativeQuery 쓸때, JPQL
//    테이블 컬럼 10개인데 그중에 3개만 쓰고 싶을때. repository 에서 사용
// 2. 클래스 Dto : 엔티티 10개중에 3개만 프론트에 전달하고싶을떄. 서비스/컨트롤러 관계에서 쓴다.
//    repository에서 쿼리 *로 10개 받았는데, 필드 3개만. 클래스 Dto는 repository에서 쓰는게 복잡해서(생성자 만들기 등등) 인터페이스 Dtoㅇ사용함.
//    modelMapper 사용
//    인터페이스 Dto : projection. dto를 확장한 기능

     long getFreeBoardId();
     String getUserId();
     String getTitle();
     String getInsertTime();
     long getLikes();

}
