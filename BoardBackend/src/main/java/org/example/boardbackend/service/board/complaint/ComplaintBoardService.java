package org.example.boardbackend.service.board.complaint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.complaint.ComplaintBoard;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName : org.example.boardbackend.service.board.complaint
 * fileName : ComplaintBoardService
 * author : hayj6
 * date : 2024-05-30(030)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-30(030)         hayj6          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ComplaintBoardService {
    private final ComplaintBoardRepository complaintBoardRepository;

    //    todo 전체 조회
    public Page<ComplaintBoard> findComplaintBoardByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<ComplaintBoard> page
                = complaintBoardRepository.findComplaintBoardByTitleContaining(
                title,
                pageable
        );
        return page;
    }
}
