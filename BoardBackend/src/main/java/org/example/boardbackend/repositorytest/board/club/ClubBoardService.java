package org.example.boardbackend.repositorytest.board.club;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.board.club.CreateClubArticleDto;
import org.example.boardbackend.model.entity.board.club.ClubBoard;
import org.example.boardbackend.model.entity.board.club.FieldPic;
import org.example.boardbackend.repository.board.club.ClubBoardRepository;
import org.example.boardbackend.repository.board.club.FieldPicRepository;
import org.example.boardbackend.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final UserRepository userRepository;

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

//  TODO: 사진 같이 저장하는 함수
//    public ClubBoard saveClubBoard(long clubBoardId,
//                                   String userId,
//                                   long likes,
//                                   String content,
//                                   String location,
//                                   String address,
//                                   long participationFee,
//                                   String startTime,
//                                   String endTime,
//                                   String recruitmentDeadline,
//                                   long maxQuota,
//                                   long minQuota,
//                                   String peoplesMatch,
//                                   String sex,
//                                   String matchForm,
//                                   String title,
//                                   String uuid,
//                                   String imgUrl,
//                                   String imgFile
//                                   ) {
//        ClubBoard clubBoard2 = null;
//        try {
//            if (clubBoardId == 0) {
//                uuid = UUID.randomUUID().toString().replace("-", ""); // uuid 만드는 방법
//
//                String imgDownload = ServletUriComponentsBuilder
//                        .fromCurrentContextPath()
//                        .path("/api/user/board/club/img/")
//                        .path(uuid)
//                        .toUriString();
//
//                ClubBoard clubBoard = new ClubBoard(userId,
//                        likes, content, location, address, participationFee,
//                        startTime, endTime, recruitmentDeadline, maxQuota, minQuota,
//                        peoplesMatch, sex, matchForm, title, imgDownload, uuid);
//                clubBoard2 = clubBoardRepository.save(clubBoard);
//            } else {
//                String imgDownload = ServletUriComponentsBuilder
//                        .fromCurrentContextPath()
//                        .path("/api/user/board/club/img/")
//                        .path(uuid)
//                        .toUriString();
//
//                ClubBoard clubBoard = new ClubBoard(userId,
//                        likes, content, location, address, participationFee,
//                        startTime, endTime, recruitmentDeadline, maxQuota, minQuota,
//                        peoplesMatch, sex, matchForm, title, imgDownload, uuid);
//                clubBoard2 = clubBoardRepository.save(clubBoard);
//            }
//        } catch (Exception e) {
//            log.debug(e.getMessage());
//        }
//        return clubBoard2;
//    }

//  TODO: 저장 함수
//@Transactional
//public void save(CreateClubArticleDto dto) {
//    // ClubBoard 저장
//    ClubBoard clubBoard = ClubBoard.builder()
//            .userId(dto.getUserId())
//            .likes(dto.getLike())
//            .content(dto.getContent())
//            .location(dto.getLocation())
//            .address(dto.getAddress())
//            .participationFee(dto.getParticipationFee())
//            .startTime(dto.getStartTime())
//            .endTime(dto.getEndTime())
//            .recruitmentDeadline(dto.getRecruitmentDeadline())
//            .maxQuota(dto.getMaxQuota())
//            .minQuota(dto.getMinQuota())
//            .peoplesMatch(dto.getPeoplesMatch())
//            .material(dto.getMaterial())
//            .sex(dto.getSex())
//            .matchForm(dto.getMatchForm())
//            .title(dto.getTitle())
//            .build();
//    clubBoard = clubBoardRepository.save(clubBoard);
//
//    // FieldPic 저장
//    if (dto.getFieldPics() != null) {
//        for (FieldPicDto fieldPicDto : dto.getFieldPics()) {
//            FieldPic fieldPic = FieldPic.builder()
//                    .uuid(UUID.randomUUID().toString())
//                    .clubBoard(clubBoard)
//                    .imgUrl(fieldPicDto.getImgUrl())
//                    .imgFile(fieldPicDto.getImgFile())
//                    .build();
//            fieldPicRepository.save(fieldPic);
//        }
//    }
//}
//@Transactional
//public void save(CreateClubArticleDto dto) {
//    // ClubBoard 저장
//    ClubBoard clubBoard = ClubBoard.builder()
//            .userId(dto.getUserId())
//            .likes(dto.getLike())
//            .content(dto.getContent())
//            .location(dto.getLocation())
//            .address(dto.getAddress())
//            .participationFee(dto.getParticipationFee())
//            .startTime(dto.getStartTime())
//            .endTime(dto.getEndTime())
//            .recruitmentDeadline(dto.getRecruitmentDeadline())
//            .maxQuota(dto.getMaxQuota())
//            .minQuota(dto.getMinQuota())
//            .peoplesMatch(dto.getPeoplesMatch())
//            .material(dto.getMaterial())
//            .sex(dto.getSex())
//            .matchForm(dto.getMatchForm())
//            .title(dto.getTitle())
//            .build();
//    clubBoard = clubBoardRepository.save(clubBoard);
//
//    // FieldPic 저장
//    if (dto.getFieldPics() != null) {
//        for (FieldPicDto fieldPicDto : dto.getFieldPics()) {
//            byte[] imgFileBytes = Base64.getDecoder().decode(fieldPicDto.getImgFile());
//            FieldPic fieldPic = FieldPic.builder()
//                    .uuid(UUID.randomUUID().toString())
//                    .clubBoard(clubBoard)
//                    .imgUrl(fieldPicDto.getImgUrl())
//                    .imgFile(imgFileBytes)
//                    .build();
//            fieldPicRepository.save(fieldPic);
//        }
//    }
//}

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
                    byte[] imgFileBytes = imgFile.getBytes(); // IOException 발생 가능
                    FieldPic fieldPic = FieldPic.builder()
                            .uuid(UUID.randomUUID().toString())
                            .clubBoard(clubBoard)
                            .imgUrl(imgFile.getOriginalFilename())
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

//@Transactional
//public void save(CreateClubArticleDto dto) {
//    ClubBoard clubBoard = ClubBoard.builder()
//            .userId(dto.getUserId())
//            .likes(dto.getLikes())
//            .content(dto.getContent())
//            .location(dto.getLocation())
//            .address(dto.getAddress())
//            .participationFee(dto.getParticipationFee())
//            .startTime(dto.getStartTime())
//            .endTime(dto.getEndTime())
//            .recruitmentDeadline(dto.getRecruitmentDeadline())
//            .maxQuota(dto.getMaxQuota())
//            .minQuota(dto.getMinQuota())
//            .peoplesMatch(dto.getPeoplesMatch())
//            .material(dto.getMaterial())
//            .sex(dto.getSex())
//            .matchForm(dto.getMatchForm())
//            .title(dto.getTitle())
//            .build();
//    clubBoard = clubBoardRepository.save(clubBoard);
//
//    if (dto.getFieldPics() != null) {
//        for (FieldPicDto fieldPicDto : dto.getFieldPics()) {
//            byte[] imgFileBytes = Base64.getDecoder().decode(fieldPicDto.getImgFile());
//            FieldPic fieldPic = FieldPic.builder()
//                    .uuid(UUID.randomUUID().toString())
//                    .clubBoard(clubBoard)
//                    .imgUrl(fieldPicDto.getImgUrl())
//                    .imgFile(imgFileBytes)
//                    .build();
//            fieldPicRepository.save(fieldPic);
//        }
//    }
//}

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


//    @Transactional
//    public void createClubBoardAndFieldPic(ClubBoard clubBoard, FieldPic fieldPic) {
//        ClubBoard savedClubBoard = clubBoardRepository.save(clubBoard);
//        fieldPic.setClubBoardId(savedClubBoard.getClubBoardId());
//        fieldPicRepository.save(fieldPic);
//    }
}
