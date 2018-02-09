package com.sunday.engine.model;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.MovementState;
import com.sunday.engine.common.Outlook;
import com.sunday.engine.common.PhysicReflection;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.Port;
import com.sunday.engine.event.Event;
import com.sunday.engine.event.EventProcessor;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.DataCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractModel implements Disposable {

    protected List<EventProcessor> eventProcessors = new ArrayList<>();
    public Port port;

    protected void addEventProcessors(EventProcessor... eventProcessors) {
        Collections.addAll(this.eventProcessors, eventProcessors);
    }

    public void notifyEvent(Event event) {
        eventProcessors.forEach(eventProcessor -> eventProcessor.processEvent(event));
    }

    public Outlook outlook = new Outlook();
    public PhysicReflection physicReflection = new PhysicReflection();
    public MovementState movementState = new MovementState();

    public void connectToDataBank(DataBank dataBank) {
        this.port = dataBank.getPort(this);
        port.addDataInstance(outlook);
        port.addDataInstance(physicReflection);
        port.addDataInstance(movementState);
        port.addDataInstance(new Rule(outlookModifiedCondition, outlookModifiedReaction));
        port.addDataInstance(new Rule(physicReflectionModifiedCondition, physicReflectionModifiedReaction));
        initialPort(port);
    }

    private Condition physicReflectionModifiedCondition = DataCondition.dataSignals(physicReflection, DataSignal.Modification);

    private Condition outlookModifiedCondition = DataCondition.dataSignals(outlook, DataSignal.Modification);

    private Reaction outlookModifiedReaction = new Reaction() {
        @Override
        public void run() {
            physicReflection.bodyDef.position.set(movementState.position);
            switch (physicReflection.fixtureDef.shape.getType()) {
                case Chain:
                    break;
                case Polygon:
                    ((PolygonShape) physicReflection.fixtureDef.shape).setAsBox(outlook.dimension.x, outlook.dimension.y);
                    break;
                case Circle:
                    ((CircleShape) physicReflection.fixtureDef.shape).setRadius((outlook.dimension.x + outlook.dimension.y) / 2);
                    break;
                case Edge:
                    break;
            }
        }
    };


    private Reaction physicReflectionModifiedReaction = new Reaction() {
        @Override
        public void run() {
            movementState.position.set(physicReflection.body.getPosition());
        }
    };

    protected abstract void initialPort(Port port);
}
