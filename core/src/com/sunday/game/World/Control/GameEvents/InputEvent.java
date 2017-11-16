package com.sunday.game.World.Control.GameEvents;


import com.sunday.game.World.Control.EventType;
import com.sunday.game.World.Control.GameEvent;

public class InputEvent extends GameEvent {

    private InputSource inputSource;

    public InputEvent(InputSource inputSource) {
        super(inputSource, EventType.Input);
        this.inputSource = inputSource;
    }

}
