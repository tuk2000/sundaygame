package com.sunday.engine.driver.mouse;

import com.sunday.engine.common.SpecificSignal;

public enum MouseSignal implements SpecificSignal {
    None, Moved, Pressed, Released, Dragged,
}
