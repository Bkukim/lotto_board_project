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

    //   todo:  저장 함수
    public FreeBoard save(FreeBoard freeBoard) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        FreeBoard freeBoard1 = freeBoardRepository.save(freeBoard);
        return freeBoard1;
    }

    // todo: 삭제
    public boolean removeById(long freeBoardId) {
//        JPA 삭제함수 : deleteById(기본키)
//        1) 먼저 기본키가 테이블에 있으면 삭제, true 리턴
//           없으면 false 리턴
//        사용법 : jpa레포지토리.existsById(기본키)
//         => 기본키가 테이블에 있으지 확인. 있으면 true, 없으면 false
        if(freeBoardRepository.existsById(freeBoardId) == true) {
            freeBoardRepository.deleteById(freeBoardId);
            return true;
        } else {
            return false;
        }
    }
}
