package com.sunday.game.engine.control.events;


public class InputEvent extends Event {

    private InputSource inputSource;

    public InputEvent(InputSource inputSource) {
        super(inputSource, EventType.Input);
        this.inputSource = inputSource;
    }

}
