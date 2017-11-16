package com.sunday.game.GameFramework.Input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;

public final class UserInputManager {
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private FrameworkInputProcessor frameworkInputProcessor = new FrameworkInputProcessor();
    private InputReceiver inputReceiver = null;

    public UserInputManager() {
        inputMultiplexer.addProcessor(frameworkInputProcessor);
    }

    public synchronized void setInputReceiver(InputReceiver inputReceiver) {
        if (this.inputReceiver != null) {
            inputMultiplexer.removeProcessor(this.inputReceiver.getInputAdapter());
        }
        inputMultiplexer.addProcessor(inputReceiver.getInputAdapter());
        this.inputReceiver = inputReceiver;
    }

    public InputProcessor getInputProcessor() {
        return inputMultiplexer;
    }

}
