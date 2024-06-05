package org.example.boardbackend.repository.notice;

import org.example.boardbackend.model.entity.notice.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : org.example.boardbackend.repository.notice
 * fileName : EventReposiotry
 * author : PC
 * date : 2024-06-03
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-03         PC          최초 생성
 */
public interface WinnerRepository extends JpaRepository<Winner,Long> {

}
