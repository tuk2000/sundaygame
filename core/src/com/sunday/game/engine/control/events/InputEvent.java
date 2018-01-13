package com.sunday.game.engine.control.events;


public class InputEvent extends Event {
    public enum InputSource {
        Keyboard, TouchScreen, Mouse, GamePad
    }

    private InputSource inputSource;

    public InputEvent(InputSource inputSource) {
        super(inputSource, EventType.Input);
        this.inputSource = inputSource;
    }

}
