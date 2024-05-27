package org.example.boardbackend.service.board.free;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.repository.board.free.FreeBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board.free
 * fileName : FreeBoardService
 * author : hayj6
 * date : 2024-05-27(027)
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27(027)         hayj6          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;

//    todo 전체 조회
    public Page<FreeBoard> selectByTitleContaining(
            String title,
            Pageable pageable
    ) {
        Page<FreeBoard> page
                = freeBoardRepository.findAllByTitleContaining(
                title,
                pageable
        );
        return page;
    }

    //   todo 상세조회
    public Optional<FreeBoard> findById(long freeBoardId) {
//        DB 상세조회 실행
        Optional<FreeBoard> freeBoardOptional
                = freeBoardRepository.findById(freeBoardId); //crud레포짓토리
        return freeBoardOptional;
    }
}
