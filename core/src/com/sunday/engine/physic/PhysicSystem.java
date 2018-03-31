package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.model.property.PhysicReflectionSignal;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class PhysicSystem extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    private Rule physicReflectionDataRule = new Rule(PhysicReflection.class, DataSignal.class, new Reaction<PhysicReflection, DataSignal>() {
        @Override
        public void accept(PhysicReflection physicReflection, DataSignal dataSignal) {
            switch (dataSignal) {
                case Add:
                    if (physicReflection.bodyCreated) {
                        world.destroyBody(physicReflection.body);
                        physicReflection.bodyCreated = false;
                    }
                    physicReflection.bodyCreated = true;
                    physicReflection.body = world.createBody(physicReflection.bodyDef);
                    physicReflection.fixture = physicReflection.body.createFixture(physicReflection.fixtureDef);
                    physicReflection.fixture.setUserData(physicReflection.owner);
                    break;
                case Deletion:
                    physicReflection.reset();
            }
        }
    });

    private Rule physicReflectionModificationRule = new Rule(PhysicReflection.class, PhysicReflectionSignal.class, new Reaction<PhysicReflection, PhysicReflectionSignal>() {
        @Override
        public void accept(PhysicReflection physicReflection, PhysicReflectionSignal physicReflectionSignal) {
            switch (physicReflectionSignal) {
                case None:
                case Updated:
            }
        }
    });


    public PhysicSystem(SystemPort systemPort) {
        super("PhysicSystem", systemPort);
        world = new World(defaultGravity, false);
        systemPort.addDataInstance(physicReflectionDataRule);
        systemPort.addDataInstance(physicReflectionModificationRule);
    }

    public World getWorld() {
        return world;
    }

    public void setContactListener(ContactListener contactListener) {
        world.setContactListener(contactListener);
    }

    public void worldStep() {
        world.step(1 / 60f, 8, 3);
    }

    @Override
    public void dispose() {
        systemPort.deleteDataInstance(physicReflectionDataRule);
        systemPort.deleteDataInstance(physicReflectionModificationRule);
        world.dispose();
    }
}
