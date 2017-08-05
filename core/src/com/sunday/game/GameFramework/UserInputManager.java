package com.sunday.game.GameFramework;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class UserInputManager implements InputProcessor {
    private static UserInputManager userInputManager = null;
    private static InputReciver inputReciver = null;
    private static AtomicBoolean secureTransmit= new AtomicBoolean(false);

    private UserInputManager() {

    }

    public static final synchronized UserInputManager getInstance() {
        if (userInputManager == null) {
            userInputManager = new UserInputManager();
        }
        return userInputManager;
    }

    public static final synchronized void setInputReciver(InputReciver inputReciver) {
        secureTransmit.set(false);
        UserInputManager.inputReciver = inputReciver;
        secureTransmit.set(true);
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.ESCAPE)
        {
            System.out.println("Key Esc pressed ");
            GameFlowManager.getInstance().setGameStatus(GameStatus.Intro);
        }
        return secureTransmit.get() ? inputReciver.getInputAdapter().keyDown(keycode) : false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().keyUp(keycode) : false;
    }

    @Override
    public boolean keyTyped(char character) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().keyTyped(character) : false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().touchDown(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().touchUp(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().touchDragged(screenX, screenY, pointer) : false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().mouseMoved(screenX, screenY) : false;
    }

    @Override
    public boolean scrolled(int amount) {
        return secureTransmit.get() ? inputReciver.getInputAdapter().scrolled(amount) : false;
    }
}
