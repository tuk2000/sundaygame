package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.sunday.game.framework.display.ScreenGenerator;

import java.util.ArrayList;
import java.util.List;

public class GameScreenGenerator implements ScreenGenerator {

    List<Class<? extends Screen>> classes = new ArrayList<>();

    public GameScreenGenerator() {
        classes.add(GamePlay.class);
        classes.add(GameSetting.class);
        classes.add(GamePlay.class);
        classes.add(GamePause.class);
        classes.add(GameTest.class);
        classes.add(DemoCannon.class);
        classes.add(DemoBallRolling.class);
        classes.add(RenderTest.class);
    }

    @Override
    public Screen generateIntroScreen() {
        return new GameIntro();
    }

    @Override
    public Screen generateLoadingScreen() {
        return new GameLoading();
    }

    @Override
    public <T extends Screen> T generateScreen(Class<T> screenClass) {
        T t = null;
        try {
            t = screenClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<Class<? extends Screen>> getScreenClasses() {
        List<Class<? extends Screen>> list = new ArrayList<>();
        list.addAll(classes);
        return list;
    }
}
