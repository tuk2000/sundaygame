package com.sunday.engine.event.driver;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadSignal;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.driver.mouse.MouseSignal;
import com.sunday.engine.event.EventTransfer;

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
        defaultKeyBoard.keyBoardSignal = KeyBoardSignal.Pressed;
        defaultKeyBoard.keyCode = keycode;
        defaultKeyBoard.character = Input.Keys.toString(keycode);
        eventPoster.dispatchEvent(new KeyBoardEvent(defaultKeyBoard));
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        defaultKeyBoard.reset();
        defaultKeyBoard.keyBoardSignal = KeyBoardSignal.Released;
        defaultKeyBoard.keyCode = keycode;
        defaultKeyBoard.character = Input.Keys.toString(keycode);
        eventPoster.dispatchEvent(new KeyBoardEvent(defaultKeyBoard));
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        defaultKeyBoard.reset();
        defaultKeyBoard.keyBoardSignal = KeyBoardSignal.Typed;
        defaultKeyBoard.keyCode = Input.Keys.valueOf(String.valueOf(character).toUpperCase());
        defaultKeyBoard.character = String.valueOf(character);
        eventPoster.dispatchEvent(new KeyBoardEvent(defaultKeyBoard));
        return true;
    }

    //TouchScreen
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        defaultMouse.reset();
        defaultMouse.mouseSignal = MouseSignal.Pressed;
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        defaultMouse.key = button;
        eventPoster.dispatchEvent(new MouseEvent(defaultMouse));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        defaultMouse.reset();
        defaultMouse.mouseSignal = MouseSignal.Released;
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        defaultMouse.key = button;
        eventPoster.dispatchEvent(new MouseEvent(defaultMouse));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        defaultMouse.reset();
        defaultMouse.mouseSignal = MouseSignal.Draged;
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        eventPoster.dispatchEvent(new MouseEvent(defaultMouse));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        defaultMouse.reset();
        defaultMouse.mouseSignal = MouseSignal.Moved;
        defaultMouse.screenX = screenX;
        defaultMouse.screenY = screenY;
        eventPoster.dispatchEvent(new MouseEvent(defaultMouse));
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    //GamePad
    @Override
    public void connected(Controller controller) {
        GamePad gamePad = new GamePad();
        gamePad.controller = controller;
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.Connect;
        driverSystem.addDriver(gamePad);
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
    }

    @Override
    public void disconnected(Controller controller) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.Disconnect;
        driverSystem.removeDriver(gamePad);
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.ButtonDown;
        gamePad.buttonCode = buttonCode;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.ButtonUp;
        gamePad.buttonCode = buttonCode;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.AxisMove;
        gamePad.axisCode = axisCode;
        gamePad.axisMoveValue = value;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.PovMove;
        gamePad.povCode = povCode;
        gamePad.povDirection = value;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.XSliderMove;
        gamePad.sliderCode = sliderCode;
        gamePad.sliderMoveValue = value;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.YSliderMove;
        gamePad.sliderCode = sliderCode;
        gamePad.sliderMoveValue = value;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        GamePad gamePad = driverSystem.getMatchGamePad(controller);
        gamePad.reset();
        gamePad.gamePadSignal = GamePadSignal.AccelerometerMove;
        gamePad.accelerometerCode = accelerometerCode;
        gamePad.accelerometerMoveValue = value;
        eventPoster.dispatchEvent(new GamePadEvent(gamePad));
        return true;
    }
}
