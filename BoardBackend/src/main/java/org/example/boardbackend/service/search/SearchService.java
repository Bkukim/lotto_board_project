package org.example.boardbackend.service.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.club.ClubBoardDto;
import org.example.boardbackend.model.dto.board.complaint.ComplaintBoardDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.board.club.QClubBoard;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.example.boardbackend.repository.board.complaint.ComplaintBoardRepository;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.example.boardbackend.repository.notice.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : org.example.boardbackend.service.search
 * fileName : SearchService
 * author : PC
 * date : 2024-06-17
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-17         PC          최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {

    private final NoticeRepository noticeRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final ClubBoardRepository clubBoardRepository;
    private final ComplaintBoardRepository complaintBoardRepository;

    public Map<String,Object> searchAll(String title,
                                        int page,
                                        int size){
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = new HashMap<>();

        Page<INoticeDto> notices = noticeRepository.findByTitleContaining(title, pageable);
        response.put("notices", notices.getContent());
        response.put("noticesTotalItems", notices.getTotalElements());

        Page<FreeBoardDto> freeBoards = freeBoardRepository.findAllByTitleContaining(title, pageable);
        response.put("freeBoards", freeBoards.getContent());
        response.put("freeBoardsTotalItems", freeBoards.getTotalElements());

        Page<ClubBoardDto> clubBoards = clubBoardRepository.findAllByTitleContaining(title, pageable);
        response.put("clubBoards", clubBoards.getContent());
        response.put("clubBoardsTotalItems", clubBoards.getTotalElements());

        Page<ComplaintBoardDto> complaintBoards = complaintBoardRepository.findComplaintBoardByTitleContaining(title, pageable);
        response.put("complaintBoards", complaintBoards.getContent());
        response.put("complaintBoardsTotalItems", complaintBoards.getTotalElements());

        return response;
    }
}
