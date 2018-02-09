package com.sunday.game.framework.gameflow;


import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameFlow<T extends Screen> {
    private ArrayList<T> flow = new ArrayList<>();
    private Map<String, Class<T>> classMap = new HashMap<>();
    private Screen loadingScreen = null;
    private Screen introScreen = null;

    GameFlow(List<Class<T>> list) {
        list.forEach(screenClass -> classMap.put(screenClass.getCanonicalName(), screenClass));
    }

    public Class<T> getScreenClass(String className) {
        return classMap.get(className);
    }

    public T current() {
        return flow.get(flow.size() - 1);
    }

    public void appendScreen(T t) {
        flow.add(t);
    }

    public T popCurrentScreen() {
        T t = current();
        flow.remove(flow.size() - 1);
        return t;
    }


    public Screen getLoadingScreen() {
        return loadingScreen;
    }

    public void setLoadingScreen(Screen loadingScreen) {
        this.loadingScreen = loadingScreen;
    }

    public Screen getIntroScreen() {
        return introScreen;
    }

    public void setIntroScreen(Screen introScreen) {
        this.introScreen = introScreen;
    }

    public int normalScreenAmount() {
        return flow.size();
    }

}
