package org.example.boardbackend.controller.user.board.club;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.example.boardbackend.model.dto.board.club.ClubBoardWithPicsDto;

import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.dto.board.club.FieldPicDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.repositorytest.board.club.ClubBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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


//  TODO: 전체 조회 함수 : 페이징 처리
@GetMapping("/club")
public ResponseEntity<List<ClubBoard>> getAllClubs() {
    List<ClubBoard> clubBoards = clubBoardService.getAllClub();
    return ResponseEntity.ok(clubBoards);
}


//  TODO: 상세 조회 함수
    @GetMapping("/club/{clubBoardId}")
    public ResponseEntity<List<ClubBoardWithPicsDto>> getClubBoard(@PathVariable long clubBoardId) {
        List<ClubBoardWithPicsDto> result = clubBoardService.getClubBoardWithPics(clubBoardId);
        return ResponseEntity.ok(result);
    }

//  TODO: 저장 함수 : ClubBoardEntity + FieldPic 동시에 생성
    @PostMapping("/club/create")
    public ResponseEntity<String> createClubArticle(
            @RequestPart("data") String data,
            @RequestPart(value = "imgFiles", required = false) MultipartFile[] imgFiles) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CreateClubArticleDto dto = objectMapper.readValue(data, CreateClubArticleDto.class);

            if (imgFiles != null && imgFiles.length > 0) {
                List<FieldPicDto> fieldPics = new ArrayList<>();
                for (MultipartFile imgFile : imgFiles) {
                    FieldPicDto fieldPicDto = new FieldPicDto();
                    fieldPicDto.setImgUrl(imgFile.getOriginalFilename());
                    fieldPicDto.setImgFile(Base64.getEncoder().encodeToString(imgFile.getBytes()));
                    fieldPics.add(fieldPicDto);
                }
                dto.setFieldPics(fieldPics);
            }

            clubBoardService.save(dto, imgFiles);
            return ResponseEntity.status(HttpStatus.CREATED).body("Club article created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create club article: " + e.getMessage());
        }
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


    // todo 게시글 신고함수

//    @PostMapping("/report")
//    public ResponseEntity<Object> saveReport(@RequestBody FreeBoard freeBoard,
//                                             @RequestParam String reporterUserId){
//
//    }
}
