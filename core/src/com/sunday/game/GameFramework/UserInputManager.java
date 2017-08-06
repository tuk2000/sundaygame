package com.sunday.game.GameFramework;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class UserInputManager implements InputProcessor {
    private static UserInputManager userInputManager = null;
    private static InputReceiver inputReceiver = null;
    private static AtomicBoolean secureTransmit= new AtomicBoolean(false);

    private UserInputManager() {

    }

    public static final synchronized UserInputManager getInstance() {
        if (userInputManager == null) {
            userInputManager = new UserInputManager();
        }
        return userInputManager;
    }

    public static final synchronized void setInputReceiver(InputReceiver inputReceiver) {
        secureTransmit.set(false);
        UserInputManager.inputReceiver = inputReceiver;
        secureTransmit.set(true);
    }


    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.ESCAPE)
        {
            System.out.println("Key Esc pressed ");
            GameFlowManager.getInstance().setGameStatus(GameStatus.Intro);
        }
        return secureTransmit.get() ? inputReceiver.getInputAdapter().keyDown(keycode) : false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().keyUp(keycode) : false;
    }

    @Override
    public boolean keyTyped(char character) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().keyTyped(character) : false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().touchDown(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().touchUp(screenX, screenY, pointer, button) : false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().touchDragged(screenX, screenY, pointer) : false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().mouseMoved(screenX, screenY) : false;
    }

    @Override
    public boolean scrolled(int amount) {
        return secureTransmit.get() ? inputReceiver.getInputAdapter().scrolled(amount) : false;
    }
}
