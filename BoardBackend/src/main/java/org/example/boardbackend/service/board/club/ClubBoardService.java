package org.example.boardbackend.service.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.club.*;
import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.dto.board.club.FieldPicDto;
import org.example.boardbackend.model.dto.board.free.FreeBoardDto;
import org.example.boardbackend.model.entity.auth.User;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.ClubBoardLike;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.repository.board.club.ClubBoardLikeRepository;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.example.boardbackend.repository.board.club.FieldPicRepository;
import org.example.boardbackend.repository.board.club.ParticipantsRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.*;

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
    private final ParticipantsRepository participantsRepository;
    private final ClubBoardLikeRepository clubBoardLikeRepository;

    // TODO: 전체 조회 함수
    public List<ClubBoard> getAllClub() {
        return clubBoardRepository.findAll();
    }

    // todo 검색을 위한 위치로 전체조회
    @Async
    public Page<ClubBoardDto> findClubBoardByLocationContaining(String location, Pageable pageable)
    {
        Page<ClubBoardDto> page
                = clubBoardRepository.findClubBoardByLocationContaining(location, pageable);
        return page;
    }

   // TODO: userId 기준으로 내가 작성한 글 조회
   public Page<ClubBoardDto> findClubBoardByUserIdContaining(String userId, Pageable pageable)
   {
       Page<ClubBoardDto> page
               = clubBoardRepository.findClubBoardByUserIdContaining(userId, pageable);
       return page;
   }

    //  TODO: 상세 조회
    public List<ClubBoardWithPicsDto> getClubBoardWithPics(long clubBoardId) {
        List<Map<String, Object>> results = clubBoardRepository.findClubBoardWithPics(clubBoardId);
        return results.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ClubBoardWithPicsDto mapToDto(Map<String, Object> result) {
        ClubBoardWithPicsDto dto = new ClubBoardWithPicsDto();
        dto.setClubBoardId(((BigDecimal) result.get("clubBoardId")).longValue());
        dto.setUserId((String) result.get("userId"));
        dto.setLikes(((BigDecimal) result.get("likes")).longValue());
        dto.setContent(clobToString((Clob) result.get("content")));
        dto.setLocation((String) result.get("location"));
        dto.setAddress((String) result.get("address"));
        dto.setParticipationFee(((BigDecimal) result.get("participationFee")).longValue());
        dto.setStartTime((String) result.get("startTime"));
        dto.setEndTime((String) result.get("endTime"));
        dto.setRecruitmentDeadline((String) result.get("recruitmentDeadline"));
        dto.setMaxQuota(((BigDecimal) result.get("maxQuota")).longValue());
        dto.setMinQuota(((BigDecimal) result.get("minQuota")).longValue());
        dto.setPeoplesMatch((String) result.get("peoplesMatch"));
        dto.setMaterial((String) result.get("material"));
        dto.setSex((String) result.get("sex"));
        dto.setMatchForm((String) result.get("matchForm"));
        dto.setTitle((String) result.get("title"));
        dto.setImgUrl((String) result.get("imgUrl"));
        return dto;
    }

    private String clobToString(Clob clob) {
        if (clob == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            int ch;
            Reader reader = clob.getCharacterStream();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error converting CLOB to String", e);
        }
        return sb.toString();
    }

    // TODO: 저장 함수 : ClubBoardEntity + FieldPic 동시에 생성
    private static final Logger logger = LoggerFactory.getLogger(ClubBoardService.class);

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


    // TODO: 저장 함수
    @Transactional
    public void save(CreateClubArticleDto dto, MultipartFile[] imgFiles) {
        ClubBoard clubBoard = ClubBoard.builder()
                .userId(dto.getUserId())
                .likes(dto.getLikes())
                .content(dto.getContent())
                .location(dto.getLocation())
                .address(dto.getAddress())
                .participationFee(dto.getParticipationFee())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .recruitmentDeadline(dto.getRecruitmentDeadline())
                .maxQuota(dto.getMaxQuota())
                .minQuota(dto.getMinQuota())
                .peoplesMatch(dto.getPeoplesMatch())
                .material(dto.getMaterial())
                .sex(dto.getSex())
                .matchForm(dto.getMatchForm())
                .title(dto.getTitle())
                .build();
        clubBoard = clubBoardRepository.save(clubBoard);

        if (imgFiles != null) {
            List<FieldPic> fieldPics = new ArrayList<>();
            for (MultipartFile imgFile : imgFiles) {
                try {
                    // 1. UUID 생성
                    String uuid = UUID.randomUUID().toString().replace("-", "");

                    // 2. 다운로드 URL 생성
                    String downloadUrl = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/normal/board/club/img/")
                            .path(uuid)
                            .toUriString();

                    // 3. FieldPic 생성
                    byte[] imgFileBytes = imgFile.getBytes(); // IOException 발생 가능
                    FieldPic fieldPic = FieldPic.builder()
                            .uuid(uuid)
                            .clubBoard(clubBoard)
                            .imgUrl(downloadUrl)
                            .imgFile(imgFileBytes)
                            .build();
                    fieldPics.add(fieldPic);
                } catch (IOException e) {
                    // 예외 처리 로직 추가
                    e.printStackTrace();
                    throw new RuntimeException("Failed to process image file", e);
                }
            }
            fieldPicRepository.saveAll(fieldPics);
        }
    }


    //  TODO: 수정 함수
    @Transactional
    public void update(long clubBoardId, CreateClubArticleDto dto, MultipartFile[] imgFiles) {
        // 기존 ClubBoard를 가져와 업데이트
        ClubBoard clubBoard = clubBoardRepository.findById(clubBoardId)
                .orElseThrow(() -> new RuntimeException("ClubBoard not found"));

        clubBoard.setUserId(dto.getUserId());
        clubBoard.setLikes(dto.getLikes());
        clubBoard.setContent(dto.getContent());
        clubBoard.setLocation(dto.getLocation());
        clubBoard.setAddress(dto.getAddress());
        clubBoard.setParticipationFee(dto.getParticipationFee());
        clubBoard.setStartTime(dto.getStartTime());
        clubBoard.setEndTime(dto.getEndTime());
        clubBoard.setRecruitmentDeadline(dto.getRecruitmentDeadline());
        clubBoard.setMaxQuota(dto.getMaxQuota());
        clubBoard.setMinQuota(dto.getMinQuota());
        clubBoard.setPeoplesMatch(dto.getPeoplesMatch());
        clubBoard.setMaterial(dto.getMaterial());
        clubBoard.setSex(dto.getSex());
        clubBoard.setMatchForm(dto.getMatchForm());
        clubBoard.setTitle(dto.getTitle());
        clubBoard = clubBoardRepository.save(clubBoard);

        // 기존 FieldPic 삭제
        List<FieldPic> existingPics = fieldPicRepository.findByClubBoard_ClubBoardId(clubBoardId);
        fieldPicRepository.deleteAll(existingPics);

        // 새 이미지를 추가
        if (imgFiles != null) {
            List<FieldPic> fieldPics = new ArrayList<>();
            for (MultipartFile imgFile : imgFiles) {
                try {
                    // 1. UUID 생성
                    String uuid = UUID.randomUUID().toString().replace("-", "");

                    // 2. 다운로드 URL 생성
                    String downloadUrl = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/api/normal/board/club/img/")
                            .path(uuid)
                            .toUriString();

                    // 3. FieldPic 생성
                    byte[] imgFileBytes = imgFile.getBytes(); // IOException 발생 가능
                    FieldPic fieldPic = FieldPic.builder()
                            .uuid(uuid)
                            .clubBoard(clubBoard)
                            .imgUrl(downloadUrl)
                            .imgFile(imgFileBytes)
                            .build();
                    fieldPics.add(fieldPic);
                } catch (IOException e) {
                    // 예외 처리 로직 추가
                    e.printStackTrace();
                    throw new RuntimeException("Failed to process image file", e);
                }
            }
            fieldPicRepository.saveAll(fieldPics);
        }
    }


    //  TODO: 삭제 함수
    @Transactional
    public void deleteByClubBoardId(Long clubBoardId) {
        participantsRepository.deleteByClubBoardId(clubBoardId);
        fieldPicRepository.deleteByClubBoard_ClubBoardId(clubBoardId);
        clubBoardLikeRepository.deleteByClubBoardId(clubBoardId);
        clubBoardRepository.deleteById(clubBoardId);

    }

    //  TODO: 이미지 조회 함수
    public Optional<FieldPic> findByUuid(String uuid) {
        return fieldPicRepository.findByUuid(uuid);
    }

    // 클럽보드 아이디로 UUID 목록을 조회하는 메소드
    public List<String> getImgUuidsByClubBoardId(Long clubBoardId) {
        List<FieldPic> fieldPics = fieldPicRepository.findByClubBoard_ClubBoardId(clubBoardId);
        return fieldPics.stream()
                .map(FieldPic::getUuid)
                .collect(Collectors.toList());
    }
}
