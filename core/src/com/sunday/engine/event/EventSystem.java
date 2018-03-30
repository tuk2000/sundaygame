package com.sunday.engine.event;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.event.window.Window;

public class EventSystem extends SubSystem implements EventDispatcher {
    private Window window;

    public EventSystem(SystemPort systemPort) {
        super("EventSystem", systemPort);
        window = new Window();
        systemPort.addDataInstance(window);
    }

    @Override
    public void dispatch(Event event) {
        systemPort.addDataInstance(event);
        systemPort.broadcast(event.getSource(), event.getSignal());
        systemPort.deleteDataInstance(event);
    }

    public void addEventTransfer(EventTransfer eventTransfer) {
        systemPort.addDataInstance(eventTransfer);
        eventTransfer.setEventDispatcher(this);
    }

    public void deleteEventTransfer(EventTransfer eventTransfer) {
        systemPort.deleteDataInstance(eventTransfer);
        eventTransfer.useDummyDispatcher();
    }

    public Window getWindow() {
        return window;
    }
}
