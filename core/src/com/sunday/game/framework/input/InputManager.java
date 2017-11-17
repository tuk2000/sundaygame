package com.sunday.game.framework.input;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public final class InputManager {
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private FrameworkInputProcessor frameworkInputProcessor = new FrameworkInputProcessor();
    private InputProcessor engineInputProcessor;

    public InputManager() {
        inputMultiplexer.addProcessor(frameworkInputProcessor);
    }

    public void setEngineInputProcessor(InputProcessor inputProcessor) {
        if (inputProcessor == null) return;
        if (engineInputProcessor != null) {
            inputMultiplexer.removeProcessor(engineInputProcessor);
        }
        engineInputProcessor = inputProcessor;
        inputMultiplexer.addProcessor(inputProcessor);
    }

    public InputProcessor getInputMultiplexer() {
        return inputMultiplexer;
    }

}
