package com.sunday.engine.environment.driver;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.gamepad.GamePadSignal;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseSignal;

public class DriverEnvironment implements InputProcessor, ControllerListener {
    private DriverSystem driverSystem;
    private DriverContext<KeyBoard> keyBoardDriverContext;
    private DriverContext<Mouse> mouseDriverContext;
    private GamePadHub gamePadHub;

    public DriverEnvironment(DriverSystem driverSystem) {
        this.driverSystem = driverSystem;
        keyBoardDriverContext = driverSystem.getKeyBoardDriverContext();
        mouseDriverContext = driverSystem.getMouseDriverContext();
        gamePadHub = driverSystem.getGamePadHub();
    }

    //KeyBoard
    @Override
    public boolean keyDown(int keycode) {
        KeyBoard keyBoard = keyBoardDriverContext.getData();
        keyBoard.keyCode = keycode;
        keyBoard.character = Input.Keys.toString(keycode);
        keyBoardDriverContext.setSignal(KeyBoardSignal.Pressed);
        keyBoardDriverContext.evaluate();
        keyBoard.reset();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        KeyBoard keyBoard = keyBoardDriverContext.getData();
        keyBoard.keyCode = keycode;
        keyBoard.character = Input.Keys.toString(keycode);
        keyBoardDriverContext.setSignal(KeyBoardSignal.Released);
        keyBoardDriverContext.evaluate();
        keyBoard.reset();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        KeyBoard keyBoard = keyBoardDriverContext.getData();
        keyBoard.keyCode = Input.Keys.valueOf(String.valueOf(character).toUpperCase());
        keyBoard.character = String.valueOf(character);
        keyBoardDriverContext.setSignal(KeyBoardSignal.Typed);
        keyBoardDriverContext.evaluate();
        keyBoard.reset();
        return true;
    }

    //TouchScreen
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Mouse mouse = mouseDriverContext.getData();
        mouse.screenX = screenX;
        mouse.screenY = screenY;
        mouse.key = button;
        mouseDriverContext.setSignal(MouseSignal.Pressed);
        mouseDriverContext.evaluate();
        mouse.reset();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Mouse mouse = mouseDriverContext.getData();
        mouse.screenX = screenX;
        mouse.screenY = screenY;
        mouse.key = button;
        mouseDriverContext.setSignal(MouseSignal.Released);
        mouseDriverContext.evaluate();
        mouse.reset();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Mouse mouse = mouseDriverContext.getData();
        mouse.screenX = screenX;
        mouse.screenY = screenY;
        mouseDriverContext.setSignal(MouseSignal.Dragged);
        mouseDriverContext.evaluate();
        mouse.reset();
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Mouse mouse = mouseDriverContext.getData();
        mouse.screenX = screenX;
        mouse.screenY = screenY;
        mouseDriverContext.setSignal(MouseSignal.Moved);
        mouseDriverContext.evaluate();
        mouse.reset();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }


    //GamePad

    private DriverContext<GamePad> getGamePadContext(Controller controller) {
        return gamePadHub.getGamePadDriverContext(controller);
    }

    @Override
    public void connected(Controller controller) {
        GamePad gamePad = new GamePad();
        gamePad.controller = controller;
        driverSystem.addDriver(gamePad);

        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        gamePadDriverContext.setSignal(GamePadSignal.Connect);
        gamePadDriverContext.evaluate();
        gamePad.reset();
    }


    @Override
    public void disconnected(Controller controller) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        gamePadDriverContext.setSignal(GamePadSignal.Disconnect);
        gamePadDriverContext.evaluate();

        GamePad gamePad = gamePadDriverContext.getData();
        driverSystem.removeDriver(gamePad);
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.buttonCode = buttonCode;
        gamePadDriverContext.setSignal(GamePadSignal.ButtonDown);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.buttonCode = buttonCode;
        gamePadDriverContext.setSignal(GamePadSignal.ButtonUp);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.axisCode = axisCode;
        gamePad.axisMoveValue = value;
        gamePadDriverContext.setSignal(GamePadSignal.AxisMove);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.povCode = povCode;
        gamePad.povDirection = value;
        gamePadDriverContext.setSignal(GamePadSignal.PovMove);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.xSliderCode = sliderCode;
        gamePad.xSliderMoveValue = value;
        gamePadDriverContext.setSignal(GamePadSignal.XSliderMove);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.ySliderCode = sliderCode;
        gamePad.ySliderMoveValue = value;
        gamePadDriverContext.setSignal(GamePadSignal.YSliderMove);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        DriverContext<GamePad> gamePadDriverContext = getGamePadContext(controller);
        GamePad gamePad = gamePadDriverContext.getData();
        gamePad.accelerometerCode = accelerometerCode;
        gamePad.accelerometerMoveValue = value;
        gamePadDriverContext.setSignal(GamePadSignal.AccelerometerMove);
        gamePadDriverContext.evaluate();
        gamePad.reset();
        return true;
    }
}
