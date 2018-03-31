package com.sunday.engine.environment.event.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sunday.engine.environment.event.EventTransfer;

public class CollisionEventTransfer extends EventTransfer implements ContactListener {

    public CollisionEventTransfer() {
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("beginContact");
        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("endContact");
        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        System.out.println("preSolve");
        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        System.out.println("postSolve");
        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
    }
}
