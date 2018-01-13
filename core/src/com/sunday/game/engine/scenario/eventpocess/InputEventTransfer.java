package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.game.engine.control.EventPoster;
import com.sunday.game.engine.control.events.GamePadEvent;
import com.sunday.game.engine.control.events.KeyBoardEvent;

public class InputEventTransfer implements InputProcessor, ControllerListener {

    private EventPoster eventPoster;

    public InputEventTransfer(EventPoster eventPoster) {
        this.eventPoster = eventPoster;
    }

    //KeyBoard
    @Override
    public boolean keyDown(int keycode) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(true, keycode));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(false, keycode));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(character));
        return true;
    }

    //TouchScreen
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    //GamePad
    @Override
    public void connected(Controller controller) {
        eventPoster.dispatchEvent(GamePadEvent.newConnectEvent(controller));
    }

    @Override
    public void disconnected(Controller controller) {
        eventPoster.dispatchEvent(GamePadEvent.newDisconnectEvent(controller));
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        eventPoster.dispatchEvent(GamePadEvent.newButtonEvent(controller, buttonCode, false));
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        eventPoster.dispatchEvent(GamePadEvent.newButtonEvent(controller, buttonCode, true));
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        eventPoster.dispatchEvent(GamePadEvent.newAxisMoveEvent(controller, axisCode, value));
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        eventPoster.dispatchEvent(GamePadEvent.newPovMoveEvent(controller, povCode, value));
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        eventPoster.dispatchEvent(GamePadEvent.newSliderMoveEvent(controller, true, sliderCode, value));
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        eventPoster.dispatchEvent(GamePadEvent.newSliderMoveEvent(controller, false, sliderCode, value));
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        eventPoster.dispatchEvent(GamePadEvent.newAccelerometerMoveEvent(controller, accelerometerCode, value));
        return true;
    }
}
