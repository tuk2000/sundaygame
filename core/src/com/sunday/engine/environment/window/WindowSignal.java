package com.sunday.engine.environment.window;

import com.sunday.engine.common.signal.SpecificSignal;

public enum WindowSignal implements SpecificSignal {
    None, Resized, Closed, Maximum, Minimum, Hide, Show
}
