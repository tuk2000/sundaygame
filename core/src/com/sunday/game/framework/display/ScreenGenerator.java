package com.sunday.game.framework.display;

import com.badlogic.gdx.Screen;

import java.util.List;

public interface ScreenGenerator {

    Screen generateIntroScreen();

    Screen generateLoadingScreen();

    <T extends Screen> T generateScreen(Class<T> screenClass);

    List<Class<? extends Screen>> getScreenClasses();
}
