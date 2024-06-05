package org.example.boardbackend.domain;

import lombok.Getter;
import org.example.boardbackend.constant.Event;

import java.util.UUID;

@Getter
public class Gifticon {
    private Event event;
    private String code;

    public Gifticon(Event event) {
        this.event = event;
        this.code = UUID.randomUUID().toString();
    }
}
