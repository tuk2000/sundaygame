package com.sunday.game.framework.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FrameworkInputProxy implements InvocationHandler {
    private Input target;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private FrameworkInputProcessor frameworkInputProcessor = new FrameworkInputProcessor();

    public FrameworkInputProxy(Input target) {
        this.target = target;
        inputMultiplexer.addProcessor(frameworkInputProcessor);
        target.setInputProcessor(inputMultiplexer);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("setInputProcessor")) {
            inputMultiplexer.clear();
            inputMultiplexer.addProcessor(frameworkInputProcessor);
            inputMultiplexer.addProcessor((InputProcessor) args[0]);
            method.invoke(target, inputMultiplexer);
        }
        return null;
    }
}
