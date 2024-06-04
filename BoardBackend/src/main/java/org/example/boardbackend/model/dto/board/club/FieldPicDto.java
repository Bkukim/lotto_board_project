package org.example.boardbackend.model.dto.board.club;

import lombok.*;

/**
 * packageName : org.example.boardbackend.model.dto.board.club
 * fileName : FieldPicDto
 * author : BALLBAT
 * date : 2024-06-03
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03         BALLBAT          최초 생성
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FieldPicDto {
    private String imgUrl; // 이미지 url
    private String imgFile; // Base64 encoded string
}