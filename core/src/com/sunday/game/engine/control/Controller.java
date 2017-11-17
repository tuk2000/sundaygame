package com.sunday.game.engine.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.events.EventProcessor;
import com.sunday.game.engine.control.events.EventType;
import com.sunday.game.engine.control.events.GameEvent;
import com.sunday.game.engine.model.RoleModel;
import com.sunday.game.engine.view.View;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Controller implements Disposable {
    protected RoleModel roleModel;
    protected View view;
    protected EventProcessor eventProcessor;
    protected ArrayList<EventType> acceptedEventTypes = new ArrayList<>();

    public Controller(EventType... acceptedEventTypes) {
        Collections.addAll(this.acceptedEventTypes, acceptedEventTypes);
    }

    public void useRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
        this.eventProcessor = this.roleModel.getEventProcessor();
    }

    public void useView(View view) {
        this.view = view;
    }

    public void notifyGameEvent(GameEvent gameEvent) {
        if (acceptedEventTypes.contains(gameEvent.getEventType())) {
            Gdx.app.log("Controller", "Receive a GameEvent" + gameEvent.toString());
            if (eventProcessor != null)
                eventProcessor.processEvent(gameEvent);
        }

    }
}
