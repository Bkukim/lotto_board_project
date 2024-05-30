package org.example.boardbackend.service.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.example.boardbackend.repository.board.club.FieldPicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.board
 * fileName : ClubBoardService
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
@Slf4j
@Service
@RequiredArgsConstructor
public class ClubBoardService {
    ModelMapper modelMapper = new ModelMapper();
    private final ClubBoardRepository clubBoardRepository;
    private final FieldPicRepository fieldPicRepository;

    //  TODO: 전체 조회 : 페이징 처리 필요 없음
    public List<ClubBoard> findAll() {
        List<ClubBoard> clubBoards =clubBoardRepository.findAll();
        return clubBoards;
    }

//  TODO: 상세 조회
    public Optional<ClubBoard> findById(long clubBoardId) {
        Optional<ClubBoard> optionalClubBoard = clubBoardRepository.findById(clubBoardId);
        return optionalClubBoard;
    }

//  TODO: 저장 함수
    public ClubBoard save(CreateClubArticleDto createClubArticleDto) {
        ClubBoard clubBoard = modelMapper.map(createClubArticleDto, ClubBoard.class);

//      1) 저장
        ClubBoard clubBoard2 = clubBoardRepository.save(clubBoard);

        return clubBoard2;
    }

//  TODO: 수정 함수
    public ClubBoard update(ClubBoard clubBoard) {
        ClubBoard clubBoard2 = clubBoardRepository.save(clubBoard);

        return clubBoard2;
    }

//  TODO: 삭제 함수
    public boolean removeById(long clubBoardId) {
        if (clubBoardRepository.existsById(clubBoardId) == true) {
            clubBoardRepository.deleteById(clubBoardId);
            return true;
        } else {
            return false;
        }
    }

//  TODO: 사진과 함께 저장하는 함수
//@Transactional
//public void createClubBoardAndFieldPic(ClubBoard clubBoard, FieldPic fieldPic) {
//    // ClubBoard 엔티티 저장
//    ClubBoard savedClubBoard = clubBoardRepository.save(clubBoard);
//
//    // FieldPic 엔티티의 참조키 설정
//    fieldPic.setClubBoardId(savedClubBoard.getClubBoardId());
//
//    // FieldPic 엔티티 저장
//    fieldPicRepository.save(fieldPic);
//}


    @Transactional
    public void createClubBoardAndFieldPic(ClubBoard clubBoard, FieldPic fieldPic) {
        ClubBoard savedClubBoard = clubBoardRepository.save(clubBoard);
        fieldPic.setClubBoardId(savedClubBoard.getClubBoardId());
        fieldPicRepository.save(fieldPic);
    }
}
