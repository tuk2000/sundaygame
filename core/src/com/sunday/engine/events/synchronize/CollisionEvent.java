package com.sunday.engine.events.synchronize;

import com.sunday.engine.events.Event;

public class CollisionEvent extends Event {
    private Object objectA;
    private Object objectB;

    public CollisionEvent(Object sender, Object objectA, Object objectB) {
        super(sender);
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public boolean isInvolved(Object object) {
        return object.equals(objectA) || object.equals(objectB);
    }

    @Override
    public String toString() {
        return getSource().toString() + " " + objectA.toString() + " " + objectB.toString();
    }
}
