package com.sunday.game.framework.display;

import com.badlogic.gdx.Screen;

public class ScreenManager {
    private ScreenDisplay screenDisplay;
    private ScreenGenerator screenGenerator;

    public ScreenManager(ScreenDisplay screenDisplay) {
        this.screenDisplay = screenDisplay;
    }

    public void displayLoadingScreen() {
        screenDisplay.setScreen(new InternalLoadingScreen());
    }

    public void setScreenGenerator(ScreenGenerator screenGenerator) {
        this.screenGenerator = screenGenerator;
    }

    public void displayScreen(Screen screen) {
        screenDisplay.setScreen(screen);
    }

    public void displayScreen(Class<? extends Screen> screenClass) {
        try {
            screenDisplay.setScreen(screenClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
