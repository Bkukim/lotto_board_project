package org.example.boardbackend.controller.user.board.club;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;

import org.example.boardbackend.model.dto.board.club.ClubBoardWithPicsDto;

import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.dto.board.club.FieldPicDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.model.entity.board.free.FreeBoard;
import org.example.boardbackend.repository.board.club.FieldPicRepository;
import org.example.boardbackend.service.board.club.ClubBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final FieldPicRepository fieldPicRepository;


    //  TODO: 전체 조회 함수
    @GetMapping("/club")
    public ResponseEntity<List<ClubBoard>> getAllClubs() {
        List<ClubBoard> clubBoards = clubBoardService.getAllClub();
        return ResponseEntity.ok(clubBoards);
    }

    // TODO: 전체 조회 함수 : 페이징 처리
    @GetMapping("/club/pageable")
    public Page<ClubBoard> pageAllClub(Pageable pageable) {
        return clubBoardService.pageAllClub(pageable);
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

    //  TODO: 이미지 조회 함수
    @GetMapping("/club/img/{uuid}")
    public ResponseEntity<byte[]> getImage(@PathVariable String uuid) {
        Optional<FieldPic> fieldPicOpt = fieldPicRepository.findByUuid(uuid);
        if (fieldPicOpt.isPresent()) {
            FieldPic fieldPic = fieldPicOpt.get();
            String imgUrl = fieldPic.getImgUrl();

            // 이미지 확장자에 따라 Content-Type 설정
            MediaType mediaType = getMediaTypeForImage(imgUrl);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imgUrl + "\"")
                    .contentType(mediaType)
                    .body(fieldPic.getImgFile());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private MediaType getMediaTypeForImage(String imgUrl) {
        if (imgUrl.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (imgUrl.endsWith(".jpg") || imgUrl.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        } else if (imgUrl.endsWith(".gif")) {
            return MediaType.IMAGE_GIF;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    // 클럽보드 아이디로 UUID 목록을 조회하는 엔드포인트
    @GetMapping("/club/uuid/{clubBoardId}")
    public ResponseEntity<List<String>> getImgUuidsByClubBoardId(@PathVariable Long clubBoardId) {
        List<String> imgUuids = clubBoardService.getImgUuidsByClubBoardId(clubBoardId);
        return ResponseEntity.ok(imgUuids);
    }
}
