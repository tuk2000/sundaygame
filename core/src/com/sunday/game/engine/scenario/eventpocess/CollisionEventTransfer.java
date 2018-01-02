package com.sunday.game.engine.scenario.eventpocess;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sunday.game.engine.control.EventPoster;
import com.sunday.game.engine.control.events.CollisionEvent;

public class CollisionEventTransfer implements ContactListener {
    private EventPoster eventPoster;

    public CollisionEventTransfer(EventPoster eventPoster) {
        this.eventPoster = eventPoster;
    }

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getUserData() != null & contact.getFixtureA().getUserData() != null) {
            CollisionEvent collisionEvent =
                    new CollisionEvent(this, contact.getFixtureA().getUserData(), contact.getFixtureB().getUserData());
            eventPoster.dispatchEvent(collisionEvent);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
