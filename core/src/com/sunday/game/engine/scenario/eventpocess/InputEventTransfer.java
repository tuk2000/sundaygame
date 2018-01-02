package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.InputProcessor;
import com.sunday.game.engine.control.EventPoster;
import com.sunday.game.engine.control.events.KeyBoardEvent;

public class InputEventTransfer implements InputProcessor {

    private EventPoster eventPoster;

    public InputEventTransfer(EventPoster eventPoster) {
        this.eventPoster = eventPoster;
    }

    @Override
    public boolean keyDown(int keycode) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(KeyBoardEvent.Operation.KeyDown, keycode);
        eventPoster.dispatchEvent(keyBoardEvent);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(KeyBoardEvent.Operation.KeyUp, keycode);
        eventPoster.dispatchEvent(keyBoardEvent);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(character);
        eventPoster.dispatchEvent(keyBoardEvent);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
