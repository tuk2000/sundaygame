package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.scenario.Scenario;

import java.util.ArrayList;
import java.util.Vector;

public class EventDispatcher implements Disposable {
    private ArrayList<EventProcessor> internalProcessors = new ArrayList<>();
    private Vector<Event> eventQueue = new Vector<>();
    private Scenario rootScenario;
    private boolean running;
    private boolean dispatching;

    public EventDispatcher(Scenario rootScenario) {
        this.rootScenario = rootScenario;
        running = true;
        dispatching = false;
    }

    public void addInternalEventProcessor(EventProcessor eventProcessor) {
        internalProcessors.add(eventProcessor);
    }

    public void dispatchEvent(Event event) {
        eventQueue.add(event);
    }

    public void dispatchEventQueue() {
        if (!running) return;
        dispatching = true;
        eventQueue.forEach(e -> {
                    Gdx.app.log("EventDispatcher", "Dispatch a Event" + e.toString());
                    internalProcessors.forEach(eventProcessor -> eventProcessor.processEvent(e));
                    rootScenario.notifyAllRoles(e);
                }
        );
        eventQueue.clear();
        dispatching = false;
    }

    @Override
    public void dispose() {
        running = false;
        while (dispatching) ;
    }
}
