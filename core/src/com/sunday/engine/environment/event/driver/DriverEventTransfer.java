package com.sunday.engine.environment.event.driver;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.environment.driver.DriverSystem;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadSignal;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseSignal;
import com.sunday.engine.environment.event.EventTransfer;

public class DriverEventTransfer extends EventTransfer implements InputProcessor, ControllerListener {
    private DriverSystem driverSystem;
    private KeyBoard defaultKeyBoard;
    private Mouse defaultMouse;

    public DriverEventTransfer(DriverSystem driverSystem) {
        this.driverSystem = driverSystem;
        defaultKeyBoard = driverSystem.getDefaultKeyBoard();
        defaultMouse = driverSystem.getDefaultMouse();
    }

    //KeyBoard
    @Override
    public boolean keyDown(int keycode) {
        defaultKeyBoard.reset();
        defaultKeyBoard.keyCode = keycode;
        defaultKeyBoard.character = Input.Keys.toString(keycode);
        eventDispatcher.dispatch(new KeyBoardEvent(defaultKeyBoard, KeyBoardSignal.Pressed));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        defaultKeyBoard.reset();
        defaultKeyBoard.keyCode = keycode;
        defaultKeyBoard.character = Input.Keys.toString(keycode);
        eventDispatcher.dispatch(new KeyBoardEvent(defaultKeyBoard, KeyBoardSignal.Released));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        defaultKeyBoard.reset();
        defaultKeyBoard.keyCode = Input.Keys.valueOf(String.valueOf(character).toUpperCase());
        defaultKeyBoard.character = String.valueOf(character);
        eventDispatcher.dispatch(new KeyBoardEvent(defaultKeyBoard, KeyBoardSignal.Typed));
        return true;
    }

    //TouchScreen
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        defaultMouse.reset();
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        defaultMouse.key = button;
        eventDispatcher.dispatch(new MouseEvent(defaultMouse, MouseSignal.Pressed));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        defaultMouse.reset();
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        defaultMouse.key = button;
        eventDispatcher.dispatch(new MouseEvent(defaultMouse, MouseSignal.Released));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        defaultMouse.reset();
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        eventDispatcher.dispatch(new MouseEvent(defaultMouse, MouseSignal.Dragged));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        defaultMouse.reset();
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        eventDispatcher.dispatch(new MouseEvent(defaultMouse, MouseSignal.Moved));
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    //GamePad
    @Override
    public void connected(Controller controller) {
        System.out.println(controller.toString());
        GamePad gamePad = new GamePad();
        gamePad.controller = controller;
        gamePad.reset();
        driverSystem.addDriver(gamePad);
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.Connect));
    }

    @Override
    public void disconnected(Controller controller) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        driverSystem.removeDriver(gamePad);
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.Disconnect));
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.buttonCode = buttonCode;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.ButtonDown));
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.buttonCode = buttonCode;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.ButtonUp));
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.axisCode = axisCode;
        gamePad.axisMoveValue = value;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.AxisMove));
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.povCode = povCode;
        gamePad.povDirection = value;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.PovMove));
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.xSliderCode = sliderCode;
        gamePad.xSliderMoveValue = value;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.XSliderMove));
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.ySliderCode = sliderCode;
        gamePad.ySliderMoveValue = value;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.YSliderMove));
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.accelerometerCode = accelerometerCode;
        gamePad.accelerometerMoveValue = value;
        eventDispatcher.dispatch(new GamePadEvent(gamePad, GamePadSignal.AccelerometerMove));
        return true;
    }
}
