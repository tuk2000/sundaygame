package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.events.GameEvent;
import com.sunday.game.engine.scenario.GameScenarioEngine;

import java.util.Vector;

public class ScenarioEventDispatcher implements Disposable {
    private Vector<GameEvent> eventQueue = new Vector<>();
    private GameScenarioEngine gameScenarioEngine;

    public ScenarioEventDispatcher(GameScenarioEngine gameScenarioEngine) {
        this.gameScenarioEngine = gameScenarioEngine;
    }

    public void dispatchEvent(GameEvent gameEvent) {
        eventQueue.add(gameEvent);
    }

    public void dispatchEventQueue() {
        eventQueue.forEach(e -> {
                    Gdx.app.log("ScenarioEventDispatcher", "Dispatch a GameEvent" + e.toString());
                    gameScenarioEngine.getRootScenario().notifyAllRoles(e);
                }
        );
        eventQueue.clear();
    }

    @Override
    public void dispose() {
        //
    }
}
