package com.sunday.engine.environment.event.collision;

import com.sunday.engine.common.Data;
import com.sunday.engine.environment.event.Event;

public class CollisionEvent extends Event {
    private Data objectA;
    private Data objectB;

    public CollisionEvent(Data sender, Data objectA, Data objectB) {
        super(sender, CollisionSignal.Collision);
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public boolean isInvolved(Data object) {
        return object.equals(objectA) || object.equals(objectB);
    }

    @Override
    public String toString() {
        return getSource().toString() + " " + objectA.toString() + " " + objectB.toString();
    }
}
