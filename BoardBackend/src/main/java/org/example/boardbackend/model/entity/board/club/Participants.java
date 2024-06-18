package org.example.boardbackend.model.entity.board.club;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * packageName : org.example.boardbackend.model.entity.board.club
 * fileName : Participants
 * author : BALLBAT
 * date : 2024-06-14
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-14         BALLBAT          최초 생성
 */
@Entity
@Table(name = "LOTTO_PARTICIPANTS")
@SequenceGenerator(
        name = "SQ_LOTTO_PARTICIPANTS_GENERATOR",
        sequenceName = "SQ_LOTTO_PARTICIPANTS",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SQ_LOTTO_PARTICIPANTS_GENERATOR")
    @Column(name = "PARTICIPANTS_ID")
    private long participantsId;

    @Column(name = "CLUB_BOARD_ID", nullable = false)
    private long clubBoardId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "APPROVAL", length = 255, nullable = false)
    private String approval = "N";
}
