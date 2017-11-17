package com.sunday.game.World.Control.GameEvents;

import com.sunday.game.World.Control.EventType;
import com.sunday.game.World.Control.GameEvent;

public class CollisionEvent extends GameEvent {
    private Object objectA;
    private Object objectB;

    public CollisionEvent(Object sender, Object objectA, Object objectB) {
        super(sender, EventType.Collision);
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public boolean isInvolved(Object object) {
        return object.equals(objectA) || object.equals(objectB);
    }

    @Override
    public String toString() {
        return getEventType().name() + " " + getSource().toString() + " " + objectA.toString() + " " + objectB.toString();
    }
}
