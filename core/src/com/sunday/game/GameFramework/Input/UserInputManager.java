package com.sunday.game.GameFramework.Input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;

public final class UserInputManager implements InputProcessor {
    private InputReceiver inputReceiver = null;

    public final synchronized void setInputReceiver(InputReceiver inputReceiver) {
        this.inputReceiver = inputReceiver;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                GameFramework.app.log("UserInputManager", "Key Esc pressed ");
                GameFramework.GameFlow.setGameStatus(GameStatus.Intro);
                break;
            case Input.Keys.BACKSPACE:
                GameFramework.app.log("UserInputManager", "Key BACKSPACE pressed ");
                GameFramework.GameFlow.backToPreviewStatus();
                break;
            case Input.Keys.F1:
                GameFramework.app.log("UserInputManager", "Key F1 pressed ");
                GameFramework.toolApplication.switchOnOrOff();
                break;
        }
        return inputReceiver.getInputAdapter().keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return inputReceiver.getInputAdapter().keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return inputReceiver.getInputAdapter().keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return inputReceiver.getInputAdapter().touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return inputReceiver.getInputAdapter().touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return inputReceiver.getInputAdapter().touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return inputReceiver.getInputAdapter().mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        return inputReceiver.getInputAdapter().scrolled(amount);
    }
}
