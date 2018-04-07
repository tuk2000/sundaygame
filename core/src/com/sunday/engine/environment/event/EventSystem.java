package com.sunday.engine.environment.event;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextBuilder;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.event.window.Window;

public class EventSystem extends SubSystem implements EventDispatcher, SystemContextBuilder {
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
        systemPort.removeDataInstance(event);
    }

    public void addEventTransfer(EventTransfer eventTransfer) {
        systemPort.addDataInstance(eventTransfer);
        eventTransfer.setEventDispatcher(this);
    }

    public void deleteEventTransfer(EventTransfer eventTransfer) {
        systemPort.removeDataInstance(eventTransfer);
        eventTransfer.useDummyDispatcher();
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public void buildSystemContext(ContextBank contextBank) {
        contextBank.addClassContext(new ClassContext(Window.class));
    }
}
