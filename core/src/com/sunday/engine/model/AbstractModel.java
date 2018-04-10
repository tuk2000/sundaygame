package com.sunday.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.databank.Port;
import com.sunday.engine.databank.PortSharing;
import com.sunday.engine.model.property.*;
import com.sunday.engine.rule.CustomizedDataCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public abstract class AbstractModel implements PortSharing, Disposable {

    public Port port;

    public Outlook outlook = new Outlook();
    public PhysicDefinition physicDefinition = new PhysicDefinition(this);
    public Movement movement = new Movement();

    private Rule<CustomizedDataContext<Movement>> movementRule = new Rule<>(new CustomizedDataCondition<>(movement, MovementSignal.class), new Reaction<CustomizedDataContext<Movement>>() {
        @Override
        public void accept(CustomizedDataContext<Movement> movementCustomizedDataContext) {
            physicDefinition.physicBody.forceMoveTo(movement.position);
            port.broadcast(outlook, OutlookSignal.Updated);
        }
    });

    private Rule<CustomizedDataContext<Outlook>> outlookRule = new Rule<>(new CustomizedDataCondition<>(outlook, OutlookSignal.class), new Reaction<CustomizedDataContext<Outlook>>() {
        @Override
        public void accept(CustomizedDataContext<Outlook> outlookCustomizedDataContext) {
            //            physicDefinition.bodyDef.position.set(movement.position);
//        switch (physicDefinition.fixtureDef.shape.getType()) {
//            case Chain:
//                break;
//            case Polygon:
//                ((PolygonShape) physicDefinition.fixtureDef.shape).setAsBox(outlook.dimension.x, outlook.dimension.y);
//                break;
//            case Circle:
//                ((CircleShape) physicDefinition.fixtureDef.shape).setRadius((outlook.dimension.x + outlook.dimension.y) / 2);
//                break;
//            case Edge:
//                break;
        }
    });
    private Rule<CustomizedDataContext<PhysicDefinition>> physicReflectionRule = new Rule<>(new CustomizedDataCondition<>(physicDefinition, PhysicReflectionSignal.class), new Reaction<CustomizedDataContext<PhysicDefinition>>() {
        @Override
        public void accept(CustomizedDataContext<PhysicDefinition> physicReflectionCustomizedDataContext) {
            PhysicReflectionSignal physicReflectionSignal = (PhysicReflectionSignal) physicReflectionCustomizedDataContext.getSignal();
            switch (physicReflectionSignal) {
                case Updated:
                    movement.position.set(physicDefinition.physicBody.getPosition());
                    movement.speed.set(physicDefinition.physicBody.getLinearVelocity());
                    movement.angularVelocity = physicDefinition.physicBody.getAngularVelocity();
                    movement.angle = physicDefinition.physicBody.getAngle();
            }
        }
    });

    @Override
    public void connectWith(Port port) {
        this.port = port;
        port.addDataInstance(outlook);
        port.addDataInstance(physicDefinition);
        port.addDataInstance(movement);
        port.addDataInstance(movementRule);
        port.addDataInstance(outlookRule);
        port.addDataInstance(physicReflectionRule);
        connectWithInternal(port);
    }

    @Override
    public void disconnectWith(Port port) {
        port.removeDataInstance(outlook);
        port.removeDataInstance(physicDefinition);
        port.removeDataInstance(movement);
        port.removeDataInstance(movementRule);
        port.removeDataInstance(outlookRule);
        port.removeDataInstance(physicReflectionRule);
        disconnectWithInternal(port);
    }

    protected abstract void connectWithInternal(Port port);

    protected abstract void disconnectWithInternal(Port port);
}
