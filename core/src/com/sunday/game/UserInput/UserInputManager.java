package com.sunday.game.UserInput;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class UserInputManager implements InputProcessor {
    private static UserInputManager userInputManager = null;
    private InputAdapter inputAdapter = null;
    private AtomicBoolean secureTransmit;

    private UserInputManager() {
        secureTransmit = new AtomicBoolean(false);
    }

    public static final synchronized UserInputManager getInstance() {
        if (userInputManager == null) {
            userInputManager = new UserInputManager();
        }
        return userInputManager;
    }

    public final synchronized void setInputAdapter(InputAdapter inputAdapter) {
        secureTransmit.set(false);
        this.inputAdapter = inputAdapter;
        secureTransmit.set(true);
    }


    @Override
    public boolean keyDown(int keycode) {

        return secureTransmit.get() ? inputAdapter.keyDown(keycode) : false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return secureTransmit.get() ? inputAdapter.keyUp(keycode) : false;
    }

    @Override
    public boolean keyTyped(char character) {
        return secureTransmit.get() ? inputAdapter.keyTyped(character) : false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputAdapter.touchDown(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputAdapter.touchUp(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return secureTransmit.get() ? inputAdapter.touchDragged(screenX, screenY, pointer) : false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return secureTransmit.get() ? inputAdapter.mouseMoved(screenX, screenY) : false;
    }

    @Override
    public boolean scrolled(int amount) {
        return secureTransmit.get() ? inputAdapter.scrolled(amount) : false;
    }
}
