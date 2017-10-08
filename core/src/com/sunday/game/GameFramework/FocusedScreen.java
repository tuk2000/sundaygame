package com.sunday.game.GameFramework;

import com.badlogic.gdx.Screen;
import com.sunday.game.GameFramework.Input.InputReceiver;

/*
* the abstract class should used as model for all game screens
* it combines all methods from Screen and InputReceiver
* Screen can be used for GameFlowManager and InputReceiver can be used for UserInputManager
*/
public abstract class FocusedScreen implements Screen, InputReceiver {
}
