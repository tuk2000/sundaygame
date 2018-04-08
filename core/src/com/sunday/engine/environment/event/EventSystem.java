package com.sunday.engine.environment.event;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.environment.event.window.Window;
import com.sunday.engine.environment.event.window.WindowCondition;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.ContextConstructor;

public class EventSystem extends SubSystem implements EventDispatcher, ContextConstructor<WindowCondition> {
    private Window window;
    private EnvironmentDataContext<Window> windowEnvironmentDataContext;

    public EventSystem(SystemPort systemPort) {
        super("EventSystem", systemPort);
        window = new Window();
        windowEnvironmentDataContext = new EnvironmentDataContext<>(window);
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
    public boolean accept(Condition condition) {
        return condition instanceof WindowCondition;
    }

    @Override
    public void construct(WindowCondition windowCondition) {
        windowCondition.setEnvironmentContext(windowEnvironmentDataContext);
    }
}
