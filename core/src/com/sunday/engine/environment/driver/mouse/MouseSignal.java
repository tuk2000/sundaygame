package com.sunday.engine.environment.driver.mouse;

import com.sunday.engine.common.signal.SpecificSignal;

public enum MouseSignal implements SpecificSignal {
    None, Moved, Pressed, Released, Dragged,
}
