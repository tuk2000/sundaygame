package com.sunday.engine.event.collision;

import com.sunday.engine.common.Data;
import com.sunday.engine.event.Event;

public class CollisionEvent extends Event {
    private Data objectA;
    private Data objectB;

    public CollisionEvent(Data sender, Data objectA, Data objectB) {
        super(sender);
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
