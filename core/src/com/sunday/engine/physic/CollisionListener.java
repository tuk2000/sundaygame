package com.sunday.engine.physic;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sunday.engine.contextbank.ContextBank;

public class CollisionListener implements ContactListener {

    private ContextBank contextBank;

    public CollisionListener(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    private void solve(Contact contact, CollisionSignal collisionSignal) {
        PhysicBody physicBodyA = (PhysicBody) contact.getFixtureA().getUserData();
        PhysicBody physicBodyB = (PhysicBody) contact.getFixtureB().getUserData();
        PhysicBodyContext collisionContextA = contextBank.getDataContext(physicBodyA);
        PhysicBodyContext collisionContextB = contextBank.getDataContext(physicBodyB);
        collisionContextA.setSignal(collisionSignal);
        collisionContextA.setOtherPhysicBody(physicBodyA);
        collisionContextB.setSignal(collisionSignal);
        collisionContextA.setOtherPhysicBody(physicBodyB);
        collisionContextA.evaluate();
        collisionContextB.evaluate();
    }

    @Override
    public void beginContact(Contact contact) {
        solve(contact, CollisionSignal.BeginContact);
    }


    @Override
    public void endContact(Contact contact) {
        solve(contact, CollisionSignal.EndContact);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        solve(contact, CollisionSignal.PreSolve);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        solve(contact, CollisionSignal.PostSolve);
    }
}
