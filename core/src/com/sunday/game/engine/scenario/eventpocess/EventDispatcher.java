package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.scenario.Scenario;

import java.util.Vector;

public class EventDispatcher implements Disposable {
    private Vector<Event> eventQueue = new Vector<>();
    private Scenario rootScenario;

    public EventDispatcher(Scenario rootScenario) {
        this.rootScenario = rootScenario;
    }

    public void dispatchEvent(Event event) {
        eventQueue.add(event);
    }

    public void dispatchEventQueue() {
        eventQueue.forEach(e -> {
                    Gdx.app.log("EventDispatcher", "Dispatch a Event" + e.toString());
                    rootScenario.notifyAllRoles(e);
                }
        );
        eventQueue.clear();
    }

    @Override
    public void dispose() {
        //
    }
}
