package com.sunday.engine.driver.gamepad;

import com.sunday.engine.common.SpecificSignal;

public enum GamePadSignal implements SpecificSignal {
    None, Connect, Disconnect, ButtonDown, ButtonUp, AxisMove, PovMove, XSliderMove, YSliderMove, AccelerometerMove
}