package org.example.boardbackend.model.entity.board.club;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.club
 * fileName : FieldPic
 * author : BALLBAT
 * date : 2024-05-23
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23         BALLBAT          최초 생성
 */
@Entity
@Table(name = "LOTTO_FIELD_PIC")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class FieldPic {
    @Id
    private String uuid;

    private long clubBoardId; // 동아리 게시판 번호, 참조키
    private String imgUrl; // 이미지 url
    private byte[] imgFile;
}
