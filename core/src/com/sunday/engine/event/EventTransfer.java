package com.sunday.engine.event;


import com.sunday.engine.common.Data;

public class EventTransfer implements Data {
    //empty EventPoster
    protected EventPoster eventPoster = new EventPoster() {
        @Override
        public void dispatchEvent(Event event) {

        }
    };

    protected void setEventPoster(EventPoster eventPoster) {
        this.eventPoster = eventPoster;
    }
}
