package com.sunday.engine.driver.gamepad;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.driver.Driver;
import com.sunday.engine.driver.DriverType;

public class GamePad extends Driver {
    public Controller controller;
    public GamePadSignal gamePadSignal;
    public int buttonCode;

    public int axisCode;
    public float axisMoveValue;

    public int povCode;
    public PovDirection povDirection;

    public int sliderCode;
    public boolean sliderMoveValue;
    public int accelerometerCode;
    public Vector3 accelerometerMoveValue;

    public GamePad() {
        super(DriverType.GamePad);
        reset();
    }

    @Override
    public void reset() {
        gamePadSignal = GamePadSignal.None;
        buttonCode = 0;
        axisCode = 0;
        axisMoveValue = 0.0f;
        povCode = 0;
        povDirection = PovDirection.center;
        sliderCode = 0;
        sliderMoveValue = false;
        accelerometerCode = 0;
        accelerometerMoveValue = Vector3.Zero;
    }
}