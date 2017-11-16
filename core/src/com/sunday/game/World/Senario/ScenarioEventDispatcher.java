package com.sunday.game.World.Senario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Control.GameEvent;

import java.util.Vector;

public class ScenarioEventDispatcher implements Disposable {
    private Vector<GameEvent> eventQueue = new Vector<>();
    private GameScenarioEngine gameScenarioEngine;
    private Thread edt;

    public ScenarioEventDispatcher(GameScenarioEngine gameScenarioEngine) {
        this.gameScenarioEngine = gameScenarioEngine;
        edt = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    eventQueue.forEach(e -> {
                                Gdx.app.log("ScenarioEventDispatcher", "Dispatch a GameEvent" + e.getEventType().name());
                                gameScenarioEngine.getScreenScenario().notifyAllRoles(e);
                            }
                    );
                    eventQueue.clear();
                }
            }
        });
        edt.setName("Game-EDT");
        edt.setDaemon(true);
        edt.start();
    }

    public void dispatchEvent(GameEvent gameEvent) {
        eventQueue.add(gameEvent);
    }

    @Override
    public void dispose() {
        //
    }
}
