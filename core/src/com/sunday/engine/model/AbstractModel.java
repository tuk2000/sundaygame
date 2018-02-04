package com.sunday.engine.model;

import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataOperation;
import com.sunday.engine.common.MovementState;
import com.sunday.engine.common.Outlook;
import com.sunday.engine.common.PhysicReflection;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.ports.UserPort;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeEvent;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.engine.event.Event;
import com.sunday.engine.event.EventProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractModel implements Disposable {

    protected List<EventProcessor> eventProcessors = new ArrayList<>();
    public UserPort userPort;

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
        this.userPort = dataBank.getUserPort(this);
        userPort.addDataInstance(outlook);
        userPort.addDataInstance(physicReflection);
        userPort.addDataInstance(movementState);
        userPort.addDataSynchronize(outLookChangedCond, outlookWithPhysic);
        userPort.addDataSynchronize(bodyCreatedCond, movementStateWithReflection);
        initDataSynchronize(userPort);
    }

    private SynchronizeCondition bodyCreatedCond = new SynchronizeCondition(physicReflection, DataOperation.Modification);

    private SynchronizeCondition outLookChangedCond = new SynchronizeCondition(outlook, DataOperation.Modification);

    private SynchronizeExecutor outlookWithPhysic = new SynchronizeExecutor<Outlook>() {
        @Override
        public void execute(SynchronizeEvent<Outlook> synchronizeEvent) {
            if (synchronizeEvent.dataOperation == DataOperation.Modification) {
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
        }
    };


    private SynchronizeExecutor movementStateWithReflection = new SynchronizeExecutor<PhysicReflection>() {
        @Override
        public void execute(SynchronizeEvent<PhysicReflection> synchronizeEvent) {
            if (synchronizeEvent.dataOperation == DataOperation.Modification) {
                movementState.position.set(physicReflection.body.getPosition());
            }
        }
    };

    protected abstract void initDataSynchronize(UserPort userPort);
}
