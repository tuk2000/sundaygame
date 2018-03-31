package com.sunday.engine.model;

import com.badlogic.gdx.utils.Disposable;
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

    private Rule movementRule = new Rule(new DataCondition(movement, MovementSignal.class), new Reaction<Movement, MovementSignal>() {
        @Override
        public void accept(Movement movement, MovementSignal movementSignal) {
            physicReflection.forceMoveTo(movement.position);
            port.broadcast(outlook, OutlookSignal.Updated);
        }
    });

    private Rule outlookRule = new Rule(new DataCondition(outlook, OutlookSignal.class), new Reaction<Outlook, OutlookSignal>() {
        @Override
        public void accept(Outlook outlook, OutlookSignal outlookSignal) {
            physicReflection.bodyDef.position.set(movement.position);
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
//        }
        }
    });
    private Rule physicReflectionRule = new Rule(new DataCondition(physicReflection, PhysicReflectionSignal.class), new Reaction<PhysicReflection, PhysicReflectionSignal>() {
        @Override
        public void accept(PhysicReflection physicReflection, PhysicReflectionSignal physicReflectionSignal) {
            movement.position.set(physicReflection.body.getPosition());
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
        port.deleteDataInstance(outlook);
        port.deleteDataInstance(physicReflection);
        port.deleteDataInstance(movement);
        port.deleteDataInstance(movementRule);
        port.deleteDataInstance(outlookRule);
        port.deleteDataInstance(physicReflectionRule);
        disconnectWithInternal(port);
    }

    protected abstract void disconnectWithInternal(Port port);

    protected abstract void connectWithInternal(Port port);
}
