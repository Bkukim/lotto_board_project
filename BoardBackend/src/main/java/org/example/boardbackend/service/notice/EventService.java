package org.example.boardbackend.service.notice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardbackend.constant.Event;
import org.example.boardbackend.domain.EventCount;
import org.example.boardbackend.model.entity.notice.Winner;
import org.example.boardbackend.repository.notice.WinnerRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final RedisTemplate<String,Object> redisTemplate;
    private final WinnerRepository winnerRepository;

    private static final long FIRST_ELEMENT = 0;
    private static final long LAST_ELEMENT = -1;
    private static final long PUBLISH_SIZE = 10;
    private static final long LAST_INDEX = 1;
    private EventCount eventCount;

    public void setEventCount(Event event, int queue){ // 컨틀롤러에서 인벤트를 정의 하는 생성자함수, 이벤트 종류와 기프티콘의 남은 량을 넣어준다.
        this.eventCount = new EventCount(event, queue);
    }

    public boolean addQueue(Winner winner,int max){ // 대기열에 값을 추가하는 함수이다.
//        final String people = Thread.currentThread().getName(); // value 값이 될 참여인원  - 실제론 userId 사용하면 될듯
        final long now = System.currentTimeMillis(); // score 값이 될 현재 시간

        redisTemplate.opsForZSet().add(winner.getEvent_type().toString(), winner.getUser_id(), (int) now); // sorted set 의 큐대기열에 key,value,score 순으로 삽입
        Long nowPeople = redisTemplate.opsForZSet().size(winner.getEvent_type().toString());
        if (nowPeople == max ) {
            return true;
        }
        log.info("대기열에 추가 - {} ({}초)", winner.getUser_id(), now);
        return false;
    }

    public void getOrder(Event event, String userId){ // 대기열에서 자신이 몇번째에 있는지를 나타낸다.
        final long start = FIRST_ELEMENT;
        final long end = LAST_ELEMENT;

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end); // 대기열의 전체 범위

        for (Object people : queue) {
            Long rank = redisTemplate.opsForZSet().rank(event.toString(), userId); // 대기열에서 자신(VALUE)이 몇번째인지를 나타냄
            log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", userId, rank);
        }
    }

    public void publish(Event event, long noticeId){ // 기프티콘을 발행하는 함수이다. 우리 프로젝트의 경우 DB에 저장을 할것이다.
        final long start = FIRST_ELEMENT; // 반복문 시작점 = 0
        final long end = PUBLISH_SIZE - LAST_INDEX; // 반복문 끝나는 지점 == 저장 후 대기열 제거할 숫자 - 1(반복문때문에 1빼기)

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end);
        for (Object userId : queue) {
            Winner winner = new Winner((String) userId,noticeId,event);
            winnerRepository.save(winner);
            log.info("'{}'님의 {} 기프티콘이 발급되었습니다",userId, winner.getEvent_type().toString());
            redisTemplate.opsForZSet().remove(winner.getEvent_type().toString(), winner.getUser_id());
            this.eventCount.decrease();
        }
    }

//    @Scheduled(fixedDelay = 1000)
//    public void scheduler(Event event, long noticeId){
//        if(validEnd()){
//            log.info("===== 선착순 이벤트가 종료되었습니다. =====");
//            return;
//        }
//        publish(event, noticeId);
//
//    }
    @Scheduled(fixedDelay = 1000)
    public void scheduler(Event event,long noticeId){
        if(validEnd()){
            log.info("===== 선착순 이벤트가 종료되었습니다. =====");
            return;
        }
        publish(event, noticeId);
    }

    public boolean validEnd(){
        return this.eventCount != null
                ? this.eventCount.end()
                : false;
    }

    public long getSize(Event event){
        return redisTemplate.opsForZSet().size(event.toString());
    }

}
