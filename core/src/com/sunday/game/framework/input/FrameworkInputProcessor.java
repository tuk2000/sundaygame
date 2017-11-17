package com.sunday.game.framework.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.sunday.game.framework.GameFramework;
import com.sunday.game.framework.gameflow.GameStatus;

public class FrameworkInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                Gdx.app.log("FrameworkInputProcessor", "Key Esc pressed ");
                GameFramework.GameFlow.setGameStatus(GameStatus.Intro);
                return true;
            case Input.Keys.BACKSPACE:
                Gdx.app.log("FrameworkInputProcessor", "Key BACKSPACE pressed ");
                GameFramework.GameFlow.backToPreviewStatus();
                return true;
            case Input.Keys.F1:
                Gdx.app.log("FrameworkInputProcessor", "Key F1 pressed ");
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
