package com.sunday.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.databank.Port;
import com.sunday.engine.databank.PortSharing;
import com.sunday.engine.model.property.Movement;
import com.sunday.engine.model.property.MovementSignal;
import com.sunday.engine.model.property.Outlook;
import com.sunday.engine.model.property.OutlookSignal;
import com.sunday.engine.physic.*;
import com.sunday.engine.rule.CustomizedDataCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public abstract class AbstractModel implements PortSharing, Disposable {

    public Port port;

    public Outlook outlook = new Outlook();
    public PhysicDefinition physicDefinition = new PhysicDefinition(this);
    public PhysicBody physicBody = new PhysicBody(physicDefinition);
    public Movement movement = new Movement();

    private Rule<CustomizedDataContext<Movement>> movementRule = new Rule<>(new CustomizedDataCondition<>(movement, MovementSignal.class), new Reaction<CustomizedDataContext<Movement>>() {
        @Override
        public void accept(CustomizedDataContext<Movement> movementCustomizedDataContext) {
            physicBody.forceMoveTo(movement.position);
        }
    });

    private Rule<CustomizedDataContext<Outlook>> outlookRule = new Rule<>(new CustomizedDataCondition<>(outlook, OutlookSignal.class), new Reaction<CustomizedDataContext<Outlook>>() {
        @Override
        public void accept(CustomizedDataContext<Outlook> outlookCustomizedDataContext) {

        }
    });
    private Rule<PhysicBodyContext> physicBodyRule = new Rule<>(new PhysicBodyCondition(physicBody, CollisionSignal.class), new Reaction<PhysicBodyContext>() {
        @Override
        public void accept(PhysicBodyContext physicBodyContext) {
            CollisionSignal physicReflectionSignal = (CollisionSignal) physicBodyContext.getSignal();
            PhysicBody physicBody = physicBodyContext.getData();
            movement.position.set(physicBody.getPosition());
            movement.speed.set(physicBody.getLinearVelocity());
            movement.angularVelocity = physicBody.getAngularVelocity();
            movement.angle = physicBody.getAngle();
            System.out.println("AbstractModel---" + this + "---" + physicBodyContext.getSignal());
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
        port.addDataInstance(physicBodyRule);
        connectWithInternal(port);
    }

    @Override
    public void disconnectWith(Port port) {
        port.removeDataInstance(outlook);
        port.removeDataInstance(physicDefinition);
        port.removeDataInstance(movement);
        port.removeDataInstance(movementRule);
        port.removeDataInstance(outlookRule);
        port.removeDataInstance(physicBodyRule);
        disconnectWithInternal(port);
    }

    protected abstract void connectWithInternal(Port port);

    protected abstract void disconnectWithInternal(Port port);
}
