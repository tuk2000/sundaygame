package com.sunday.game.GameFramework;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class UserInputManager implements InputProcessor {
    private static UserInputManager userInputManager = null;
    private  InputReceiver inputReceiver = null;
    private  AtomicBoolean secureTransmit= new AtomicBoolean(false);

    protected UserInputManager() {

    }

    public static final synchronized UserInputManager getInstance() {
        if (userInputManager == null) {
            userInputManager = new UserInputManager();
        }
        return userInputManager;
    }

    public  final synchronized void setInputReceiver(InputReceiver inputReceiver) {
        secureTransmit.set(false);
        this.inputReceiver = inputReceiver;
        secureTransmit.set(true);
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ESCAPE:
                System.out.println("Key Esc pressed ");
                GameFlowManager.getInstance().setGameStatus(GameStatus.Intro);
                break;
            case Input.Keys.BACKSPACE:
                System.out.println("Key BACKSPACE pressed ");
                GameFlowManager.getInstance().backToPreviewStatus();
                break;
        }
        return secureTransmit.get() && inputReceiver.getInputAdapter().keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        return secureTransmit.get() && inputReceiver.getInputAdapter().scrolled(amount);
    }
}
