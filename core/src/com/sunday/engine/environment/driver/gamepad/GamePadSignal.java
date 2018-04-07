package com.sunday.engine.environment.driver.gamepad;

import com.sunday.engine.common.signal.SpecificSignal;

public enum GamePadSignal implements SpecificSignal {
    None, Connect, Disconnect, ButtonDown, ButtonUp, AxisMove, PovMove, XSliderMove, YSliderMove, AccelerometerMove
}