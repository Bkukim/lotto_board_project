package org.example.boardbackend.controller.user.board.club;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.dto.board.club.FieldPicDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.service.board.club.ClubBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private static final Logger logger = LoggerFactory.getLogger(ClubBoardController.class);

//  TODO: 전체 조회 함수
    @GetMapping("/club")
    public List<ClubBoard> findAll() {
        return clubBoardService.findAll();
    }

//  TODO: 저장 함수
//    @PostMapping("/club")
//    public ResponseEntity<Object> create(
//            @RequestBody CreateClubArticleDto createClubArticleDto
//            ) {
//        try {
//            if (createClubArticleDto == null) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
//                ClubBoard clubBoard2 = clubBoardService.save(createClubArticleDto);
//                return new ResponseEntity<>(clubBoard2, HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            log.debug("로깅 :::" + e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//  TODO: 상세 조회 함수
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
//    @PostMapping("/club/create")
//    public void createClubBoard(@RequestBody ClubBoard clubBoard,
//                                @RequestBody FieldPic fieldPic) {
//        clubBoardService.createClubBoardAndFieldPic(clubBoard, fieldPic);
//    }

//    @PostMapping("/club/create")
//    public ResponseEntity<ClubBoard> createClubArticle(@RequestBody CreateClubArticleDto dto) {
//        ClubBoard clubBoard = clubBoardService.createClubArticle(dto);
//        return ResponseEntity.ok(clubBoard);
//    }
//@PostMapping("/club/create")
//public ResponseEntity<Object> createClub(
//        @RequestParam("content") String content,
//        @RequestParam("location") String location,
//        @RequestParam("address") String address,
//        @RequestParam("participationFee") String participationFee,
//        @RequestParam("startTime") String startTime,
//        @RequestParam("endTime") String endTime,
//        @RequestParam("recruitmentDeadline") String recruitmentDeadline,
//        @RequestParam("maxQuota") String maxQuota,
//        @RequestParam("minQuota") String minQuota,
//        @RequestParam("peoplesMatch") String peoplesMatch,
//        @RequestParam("material") String material,
//        @RequestParam("matchForm") String matchForm,
//        @RequestParam("title") String title,
//        @RequestParam("imgFile") MultipartFile imgFile
//) {
//    // 데이터 처리 및 저장 로직
//    // 예시로 데이터 출력을 해봅니다.
//    System.out.println("뭔데 도대체::::::::::: " + content);
//    System.out.println("로케이션은 뭐야:::::::::::::::: " + location);
//    // ... 다른 필드들도 출력
//
//    // 실제로는 데이터 저장 로직을 여기에 작성합니다.
//    // 예외가 발생할 경우, 적절한 에러 메시지와 함께 ResponseEntity를 반환합니다.
//
//    return ResponseEntity.ok("Success");
//}
//@PostMapping("/club/create")
//public ResponseEntity<Object> createClub(
//        @RequestBody CreateClubArticleDto dto) {
//
//    try {
//        ClubBoard createdClub = clubBoardService.createClubArticle(dto);
//        return ResponseEntity.ok("Club created successfully");
//    } catch (Exception e) {
//        log.debug("로깅이요 ::::" + e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create club: " + e.getMessage());
//    }
//}

////  TODO: 저장 함수
//@PostMapping("/club/create")
//public ResponseEntity<String> createClubArticle(
//        @RequestPart("data") String data,
//        @RequestPart(value = "imgFile", required = false) MultipartFile imgFile) {
//    try {
//        ObjectMapper objectMapper = new ObjectMapper();
//        CreateClubArticleDto dto = objectMapper.readValue(data, CreateClubArticleDto.class);
//
//        if (imgFile != null && !imgFile.isEmpty()) {
//            FieldPicDto fieldPicDto = new FieldPicDto();
//            fieldPicDto.setImgUrl(imgFile.getOriginalFilename());
//            fieldPicDto.setImgFile(Base64.getEncoder().encodeToString(imgFile.getBytes()));
//            dto.setFieldPics(Collections.singletonList(fieldPicDto));
//        }
//
//        clubBoardService.save(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Club article created successfully.");
//    } catch (Exception e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create club article: " + e.getMessage());
//    }
//}

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
