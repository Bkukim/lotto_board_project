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
 * packageName : org.example.boardbackend.service.event
 * fileName : EventService
 * author : PC
 * date : 2024-05-21
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21         PC          최초 생성
 */

@Slf4j
@Service
@EnableCaching // todo 캐깅 기능을 사용하게 해주는 어노테이션
public class NoticeRedisService extends NoticeService{


    public NoticeRedisService(NoticeRepository noticeRepository) {
        super(noticeRepository);
    }

    // todo 공지사항 제목으로 검색 페이지
    @Cacheable("notices")
    public Page<INoticeDto> redisFindByTitleContaining(String title, Pageable pageable) {
        Page<INoticeDto> notices = findByTitleContaining(title, pageable);

        return notices;
    }

    // todo 상세조회
    @Cacheable("notice") //
    public Optional<Notice> redisFindById(long noticeId) {
        Optional<Notice> notice = findById(noticeId);

        return notice;
    }

    // todo 저장함수는 redis에서 이용할 수 없다.

    // todo  수정 사용법 : @CacheEvict(value = "값", key = "#객체명.속성명")
    @CacheEvict(value = "notice", key = "#notice.noticeId")
    public Notice redisUpdate(Notice notice) {

        Notice notice2 = update(notice);

        return notice2;
    }

    // todo  삭제 사용법 : @CacheEvict(value = "키이름", key = "#매개변수명")
    @CacheEvict(value = "notice", key = "#noticeId")
    public boolean redisRemoveById(long noticeId) {
        return removeById(noticeId);
    }


}
