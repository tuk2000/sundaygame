package com.sunday.engine.environment.driver.keyboard;

import com.sunday.engine.common.signal.SpecificSignal;

public enum KeyBoardSignal implements SpecificSignal {
    None, Pressed, Released, Typed
}