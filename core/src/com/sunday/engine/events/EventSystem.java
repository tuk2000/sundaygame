package com.sunday.engine.events;

import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.ports.SystemPort;

public class EventSystem extends SubSystem {
    public EventSystem(SystemPort systemPort) {
        super("EventSystem", systemPort);
    }
}
