package com.sunday.game.framework.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.sunday.game.framework.GameFramework;
import com.sunday.game.framework.gameflow.GameStatus;

public class FrameworkInputProcessor extends InputAdapter {
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
}
