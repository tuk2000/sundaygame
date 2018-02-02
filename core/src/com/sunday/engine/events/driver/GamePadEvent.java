package com.sunday.engine.events.driver;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.common.enums.Driver;

public class GamePadEvent extends DriverEvent {
    private Controller controller;
    private Operation operation;
    private int buttonCode;

    private int axisCode;
    private float axisMoveValue;

    private int povCode;
    private PovDirection povDirection;

    private boolean isXSliderMoved;
    private int sliderCode;
    private boolean sliderMoveValue;
    private int accelerometerCode;
    private Vector3 accelerometerMoveValue;

    public static GamePadEvent newConnectEvent(Controller controller) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = Operation.Connect;
        return gamePadEvent;
    }

    public static GamePadEvent newDisconnectEvent(Controller controller) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = Operation.Disconnect;
        return gamePadEvent;
    }

    public static GamePadEvent newButtonEvent(Controller controller, int buttonCode, boolean buttonDown) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = buttonDown ? Operation.ButtonDown : Operation.ButtonUp;
        gamePadEvent.buttonCode = buttonCode;
        return gamePadEvent;
    }

    public static GamePadEvent newAxisMoveEvent(Controller controller, int axisCode, float value) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = Operation.AxisMove;
        gamePadEvent.axisCode = axisCode;
        gamePadEvent.axisMoveValue = value;
        return gamePadEvent;
    }

    public static GamePadEvent newPovMoveEvent(Controller controller, int povCode, PovDirection povDirection) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = Operation.PovMove;
        gamePadEvent.povCode = povCode;
        gamePadEvent.povDirection = povDirection;
        return gamePadEvent;
    }

    public static GamePadEvent newSliderMoveEvent(Controller controller, boolean isXSliderMoved, int sliderCode, boolean value) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = isXSliderMoved ? Operation.XSliderMove : Operation.YSliderMove;
        gamePadEvent.isXSliderMoved = isXSliderMoved;
        gamePadEvent.sliderCode = sliderCode;
        gamePadEvent.sliderMoveValue = value;
        return gamePadEvent;
    }

    public static GamePadEvent newAccelerometerMoveEvent(Controller controller, int accelerometerCode, Vector3 value) {
        GamePadEvent gamePadEvent = new GamePadEvent(controller);
        gamePadEvent.operation = Operation.AccelerometerMove;
        gamePadEvent.accelerometerCode = accelerometerCode;
        gamePadEvent.accelerometerMoveValue = value;
        return gamePadEvent;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getButton() {
        return buttonCode;
    }

    public int getPov() {
        return povCode;
    }

    public PovDirection getPovDirection() {
        return povDirection;
    }

    public enum Operation {
        Connect, Disconnect, ButtonDown, ButtonUp, AxisMove, PovMove, XSliderMove, YSliderMove, AccelerometerMove
    }

    public GamePadEvent(Controller controller) {
        super(Driver.GamePad);
        this.controller = controller;
    }
}
