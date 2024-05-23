package org.example.boardbackend.service.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.model.dto.notice.INoticeDto;
import org.example.boardbackend.model.entity.notice.Notice;
import org.example.boardbackend.repository.notice.NoticeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    // todo 공지사항 제목으로 검색 페이지
    public Page<INoticeDto> findByTitleContaining(String title, Pageable pageable) {
        Page<INoticeDto> notices = noticeRepository.findByTitleContaining(title,pageable);

        return notices;
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

    // todo  삭제 함수
    public boolean removeById(long noticeId) {

        if (noticeRepository.existsById(noticeId)) {
            noticeRepository.deleteById(noticeId);
            return true;
        }
        return false;
    }

}