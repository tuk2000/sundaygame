package com.sunday.game.World.Senario;

import com.badlogic.gdx.InputProcessor;
import com.sunday.game.World.Control.GameEvents.KeyBoardEvent;

public class GameInputEventTransfer implements InputProcessor {

    private ScenarioEventDispatcher scenarioEventDispatcher;

    public GameInputEventTransfer(ScenarioEventDispatcher scenarioEventDispatcher) {
        this.scenarioEventDispatcher = scenarioEventDispatcher;
    }

    @Override
    public boolean keyDown(int keycode) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(KeyBoardEvent.Operation.KeyDown, keycode);
        scenarioEventDispatcher.dispatchEvent(keyBoardEvent);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(KeyBoardEvent.Operation.KeyUp, keycode);
        scenarioEventDispatcher.dispatchEvent(keyBoardEvent);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(character);
        scenarioEventDispatcher.dispatchEvent(keyBoardEvent);
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
