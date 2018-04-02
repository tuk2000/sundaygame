package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.model.property.PhysicReflectionSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

import static com.sunday.engine.environment.driver.gamepad.GamePadSignal.None;

public class PhysicSystem extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    private Rule physicReflectionDataRule = new Rule<>(new ClassCondition<>(PhysicReflection.class, DataSignal.class), new Reaction<ClassContext<PhysicReflection>>() {
        @Override
        public void accept(ClassContext<PhysicReflection> physicReflectionClassContext) {
            PhysicReflection physicReflection = physicReflectionClassContext.getInstance();
            DataSignal dataSignal = (DataSignal) physicReflectionClassContext.getSignal();
            switch (dataSignal) {
                case Add:
                    if (physicReflection.bodyCreated) {
                        world.destroyBody(physicReflection.body);
                        physicReflection.bodyCreated = false;
                    }
                    physicReflection.bodyCreated = true;
                    physicReflection.body = world.createBody(physicReflection.bodyDef);
                    physicReflection.fixture = physicReflection.body.createFixture(physicReflection.fixtureDef);
                    physicReflection.fixture.setUserData(physicReflection);
                    break;
                case Deletion:
                    physicReflection.reset();
            }
        }
    });

    private Rule physicReflectionModificationRule = new Rule(new ClassCondition<>(PhysicReflection.class, PhysicReflectionSignal.class), new Reaction<ClassContext<PhysicReflection>>() {
        @Override
        public void accept(ClassContext<PhysicReflection> physicReflectionClassContext) {
            PhysicReflection physicReflection = physicReflectionClassContext.getInstance();
            PhysicReflectionSignal physicReflectionSignal = (PhysicReflectionSignal) physicReflectionClassContext.getSignal();
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
        systemPort.removeDataInstance(physicReflectionDataRule);
        systemPort.removeDataInstance(physicReflectionModificationRule);
        world.dispose();
    }
}
