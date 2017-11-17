package com.sunday.game.World.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

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
