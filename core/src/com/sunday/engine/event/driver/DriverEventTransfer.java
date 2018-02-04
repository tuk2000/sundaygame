package com.sunday.engine.event.driver;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.event.EventTransfer;

public class DriverEventTransfer extends EventTransfer implements InputProcessor, ControllerListener {
    private DriverSystem driverSystem;
    private KeyBoard defaultKeyBoardData;
    private Mouse defaultMouseData;

    public DriverEventTransfer(DriverSystem driverSystem) {
        this.driverSystem = driverSystem;
        defaultKeyBoardData = driverSystem.getDefaultKeyBoardData();
        defaultMouseData = driverSystem.getDefaultMouseData();
    }

    //KeyBoard
    @Override
    public boolean keyDown(int keycode) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(defaultKeyBoardData, true, keycode));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(defaultKeyBoardData, false, keycode));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        eventPoster.dispatchEvent(KeyBoardEvent.newKeyEvent(defaultKeyBoardData, character));
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
        GamePad gamePadData = new GamePad();
        gamePadData.controller = controller;
        driverSystem.addDriverData(gamePadData);
        eventPoster.dispatchEvent(GamePadEvent.newConnectEvent(gamePadData));
    }

    @Override
    public void disconnected(Controller controller) {
        GamePad gamePadData = driverSystem.getMatchGamePadData(controller);
        driverSystem.removeDriverData(gamePadData);
        eventPoster.dispatchEvent(GamePadEvent.newDisconnectEvent(gamePadData));
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        eventPoster.dispatchEvent(GamePadEvent.newButtonEvent(driverSystem.getMatchGamePadData(controller), buttonCode, false));
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        eventPoster.dispatchEvent(GamePadEvent.newButtonEvent(driverSystem.getMatchGamePadData(controller), buttonCode, true));
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        eventPoster.dispatchEvent(GamePadEvent.newAxisMoveEvent(driverSystem.getMatchGamePadData(controller), axisCode, value));
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        eventPoster.dispatchEvent(GamePadEvent.newPovMoveEvent(driverSystem.getMatchGamePadData(controller), povCode, value));
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        eventPoster.dispatchEvent(GamePadEvent.newSliderMoveEvent(driverSystem.getMatchGamePadData(controller), true, sliderCode, value));
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        eventPoster.dispatchEvent(GamePadEvent.newSliderMoveEvent(driverSystem.getMatchGamePadData(controller), false, sliderCode, value));
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        eventPoster.dispatchEvent(GamePadEvent.newAccelerometerMoveEvent(driverSystem.getMatchGamePadData(controller), accelerometerCode, value));
        return true;
    }
}
