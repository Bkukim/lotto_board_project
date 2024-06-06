package org.example.boardbackend.domain;

import lombok.Getter;
import lombok.Setter;
import org.example.boardbackend.constant.Event;

@Getter
@Setter
public class EventCount {
    private Event event;
    private int limit;

    private static final int END = 0;

    public EventCount(Event event, int limit) {
        this.event = event;
        this.limit = limit;
    }

    public synchronized /*싱크로 나이즈는 멀티스레드 환경에서 해당 스레드가 이 자원을 사용하면 다른 스레드는 이 자원을 사용하지
    못하게 하여 데이터의 신뢰성을 보장하는 기능이다.*/void decrease(){
        this.limit--;
    }

    public boolean end(){
        return this.limit == END;
    }
}
