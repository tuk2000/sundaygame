package com.sunday.game.GameFramework.Input;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public final class UserInputManager {
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private FrameworkInputProcessor frameworkInputProcessor = new FrameworkInputProcessor();
    private InputProcessor userInputProcessor;

    public UserInputManager() {
        inputMultiplexer.addProcessor(frameworkInputProcessor);
    }

    public void setUserInputProcessor(InputProcessor inputProcessor) {
        if (inputProcessor == null) return;
        if (userInputProcessor != null) {
            inputMultiplexer.removeProcessor(userInputProcessor);
        }
        userInputProcessor = inputProcessor;
        inputMultiplexer.addProcessor(inputProcessor);
    }

    public InputProcessor getInputMultiplexer() {
        return inputMultiplexer;
    }

}
