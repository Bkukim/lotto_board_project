package org.example.boardbackend.controller.user.board.club;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.service.board.club.ClubBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.controller.user.board
 * fileName : ClubBoardController
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/board")
public class ClubBoardController {
    private final ClubBoardService clubBoardService;

//  TODO: 전체 조회 함수
    @GetMapping("/club")
    public List<ClubBoard> findAll() {
        return clubBoardService.findAll();
    }

//  TODO: 저장 함수
    @PostMapping("/club")
    public ResponseEntity<Object> create(
            @RequestBody CreateClubArticleDto createClubArticleDto
            ) {
        try {
            ClubBoard clubBoard2 = clubBoardService.save(createClubArticleDto);
            return new ResponseEntity<>(clubBoard2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//  TODO; 상세 조회 함수
    @GetMapping("/club/{clubBoardId}")
    public ResponseEntity<Object> findById(
            @PathVariable long clubBoardId
    ) {
        try {
            Optional<ClubBoard> optionalClubBoard = clubBoardService.findById(clubBoardId);

            if (optionalClubBoard.isEmpty() == true) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(optionalClubBoard.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//  TODO: 저장 함수
//    @PostMapping("/club/write/complete")
//    public ResponseEntity<Object> create
    @PostMapping("/club/create")
    public void createClubBoard(@RequestBody ClubBoard clubBoard,
                                @RequestBody FieldPic fieldPic) {
        clubBoardService.createClubBoardAndFieldPic(clubBoard, fieldPic);
    }

//  TODO: 수정 함수
    @PutMapping("/club/update/{clubBoardId}")
    public ResponseEntity<Object> update(
            @PathVariable long clubBoardId,
            @RequestBody ClubBoard clubBoard
    ) {
        try {
            ClubBoard clubBoard2 = clubBoardService.update(clubBoard);

            return new ResponseEntity<>(clubBoard2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//  TODO: 삭제 함수
    @DeleteMapping("club/deletion/{clubBoardId}")
    public ResponseEntity<Object> delete(
            @PathVariable long clubBoardId
    ) {
        try {
//          DB 서비스 삭제 함수 실행
            boolean success = clubBoardService.removeById(clubBoardId);
            if (success == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    // todo 게시글 신고함수
//    @PostMapping("/report")
//    public ResponseEntity<Object> saveReport(@RequestBody FreeBoard freeBoard,
//                                             @RequestParam String reporterUserId){
//
//    }
}
