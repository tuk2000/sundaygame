package com.sunday.engine.driver.gamepad;

import com.sunday.engine.common.Signal;

public enum GamePadSignal implements Signal {
    None, Connect, Disconnect, ButtonDown, ButtonUp, AxisMove, PovMove, XSliderMove, YSliderMove, AccelerometerMove
}