package com.sunday.game.GameFramework.Input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;

public class FrameworkInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                GameFramework.app.log("FrameworkInputProcessor", "Key Esc pressed ");
                GameFramework.GameFlow.setGameStatus(GameStatus.Intro);
                return true;
            case Input.Keys.BACKSPACE:
                GameFramework.app.log("FrameworkInputProcessor", "Key BACKSPACE pressed ");
                GameFramework.GameFlow.backToPreviewStatus();
                return true;
            case Input.Keys.F1:
                GameFramework.app.log("FrameworkInputProcessor", "Key F1 pressed ");
                GameFramework.switchToolOnOrOff();
                return false;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
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
