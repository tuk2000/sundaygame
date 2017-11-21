package com.sunday.game.engine.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.control.events.EventType;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.AbstractView;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractController implements Disposable {
    protected AbstractModel abstractModel;
    protected AbstractView abstractView;
    protected EventProcessor eventProcessor;
    protected ArrayList<EventType> acceptedEventTypes = new ArrayList<>();

    public AbstractController(EventType... acceptedEventTypes) {
        Collections.addAll(this.acceptedEventTypes, acceptedEventTypes);
    }

    public void useRoleModel(AbstractModel abstractModel) {
        this.abstractModel = abstractModel;
        this.eventProcessor = this.abstractModel.getEventProcessor();
    }

    public void useView(AbstractView abstractView) {
        this.abstractView = abstractView;
    }

    public void notifyGameEvent(Event event) {
        if (acceptedEventTypes.contains(event.getEventType())) {
            Gdx.app.log("AbstractController", "Receive a Event" + event.toString());
            if (eventProcessor != null)
                eventProcessor.processEvent(event);
        }
    }

    public void synchronize() {
        abstractModel.synchronizeTextureWithPhysic();
        abstractView.synchronizeWithRoleModel(abstractModel);
    }
}
