package org.example.boardbackend.service.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.dto.notice.NoticeAllDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.repository.notice.NoticeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName : org.example.boardbackend.service.notice
 * fileName : NoticeService
 * author : PC
 * date : 2024-05-22
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22         PC          최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    // todo 공지사항 제목으로 검색 페이지 ::전체조회
    public Page<INoticeDto> findByTitleContaining(String title, Pageable pageable) {
        Page<INoticeDto> notices = noticeRepository.findByTitleContaining(title,pageable);
        return notices;
    }
//   todo: 부서조회 , 페이징 없음
    public List<INoticeDto> findByNoticeTypeDept(INoticeDto iNoticeDto) {
        List<INoticeDto> notice = noticeRepository.findByNoticeTypeDept(iNoticeDto);
        return notice;
    }

    //   todo: 자유조회 , 페이징 없음
    public List<INoticeDto> findByNoticeTypeFree(INoticeDto iNoticeDto) {
        List<INoticeDto> notice = noticeRepository.findByNoticeTypeFree(iNoticeDto);
        return notice;
    }
    
    //   todo: 건의조회 , 페이징 없음
    public List<INoticeDto> findByNoticeTypeComplaint(INoticeDto iNoticeDto) {
        List<INoticeDto> notice = noticeRepository.findByNoticeTypeComplaint(iNoticeDto);
        return notice;
    } 
    
    //   todo: 동호회 , 페이징 없음
    public List<INoticeDto> findByNoticeTypeGroup(INoticeDto iNoticeDto) {
        List<INoticeDto> notice = noticeRepository.findByNoticeTypeGroup(iNoticeDto);
        return notice;
    }

    // todo 상세조회
    public Optional<Notice> findById(long noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);

        return notice;
    }


    // todo 수정함수
    public Notice update(Notice notice) {

        Notice notice2 = noticeRepository.save(notice);

        return notice2;
    }
// todo 저장함수
    public Notice save(Notice notice) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Notice notice1 = noticeRepository.save(notice);
        return notice1;
    }

    // todo  삭제 함수
    public boolean removeById(long noticeId) {

        if (noticeRepository.existsById(noticeId)) {
            noticeRepository.deleteById(noticeId);
            return true;
        }
        return false;
    }

}