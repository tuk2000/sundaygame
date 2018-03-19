package com.sunday.engine.event.window;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.event.Event;

public class WindowEvent extends Event {

    public WindowEvent(Data source, Signal signal) {
        super(source, signal);
    }

}
