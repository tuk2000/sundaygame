package com.sunday.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataContext;
import com.sunday.engine.databank.Port;
import com.sunday.engine.databank.PortSharing;
import com.sunday.engine.model.property.*;
import com.sunday.engine.rule.DataCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public abstract class AbstractModel implements PortSharing, Disposable {

    public Port port;

    public Outlook outlook = new Outlook();
    public PhysicReflection physicReflection = new PhysicReflection(this);
    public Movement movement = new Movement();

    private Rule movementRule = new Rule(new DataCondition(movement, MovementSignal.class), new Reaction<DataContext<Movement>>() {
        @Override
        public void accept(DataContext<Movement> movementDataContext) {
            physicReflection.forceMoveTo(movement.position);
            port.broadcast(outlook, OutlookSignal.Updated);
        }
    });

    private Rule outlookRule = new Rule(new DataCondition(outlook, OutlookSignal.class), new Reaction<DataContext<Outlook>>() {
        @Override
        public void accept(DataContext<Outlook> outlookDataContext) {
            //            physicReflection.bodyDef.position.set(movement.position);
//        switch (physicReflection.fixtureDef.shape.getType()) {
//            case Chain:
//                break;
//            case Polygon:
//                ((PolygonShape) physicReflection.fixtureDef.shape).setAsBox(outlook.dimension.x, outlook.dimension.y);
//                break;
//            case Circle:
//                ((CircleShape) physicReflection.fixtureDef.shape).setRadius((outlook.dimension.x + outlook.dimension.y) / 2);
//                break;
//            case Edge:
//                break;
        }
    });
    private Rule physicReflectionRule = new Rule(new DataCondition(physicReflection, PhysicReflectionSignal.class), new Reaction<DataContext<PhysicReflection>>() {
        @Override
        public void accept(DataContext<PhysicReflection> physicReflectionDataContext) {
            PhysicReflectionSignal physicReflectionSignal = (PhysicReflectionSignal) physicReflectionDataContext.getSignal();
            switch (physicReflectionSignal) {
                case Updated:
                    movement.position.set(physicReflection.body.getPosition());
                    movement.speed.set(physicReflection.body.getLinearVelocity());
                    movement.angularVelocity = physicReflection.body.getAngularVelocity();
                    movement.angle = physicReflection.body.getAngle();
            }
        }
    });

    @Override
    public void connectWith(Port port) {
        this.port = port;
        port.addDataInstance(outlook);
        port.addDataInstance(physicReflection);
        port.addDataInstance(movement);
        port.addDataInstance(movementRule);
        port.addDataInstance(outlookRule);
        port.addDataInstance(physicReflectionRule);
        connectWithInternal(port);
    }

    @Override
    public void disconnectWith(Port port) {
        port.removeDataInstance(outlook);
        port.removeDataInstance(physicReflection);
        port.removeDataInstance(movement);
        port.removeDataInstance(movementRule);
        port.removeDataInstance(outlookRule);
        port.removeDataInstance(physicReflectionRule);
        disconnectWithInternal(port);
    }

    protected abstract void connectWithInternal(Port port);

    protected abstract void disconnectWithInternal(Port port);
}
