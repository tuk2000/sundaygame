package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicBody;
import com.sunday.engine.model.property.PhysicDefinition;
import com.sunday.engine.model.property.PhysicReflectionSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class PhysicSystem extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    private Rule physicDefinitionRule = new Rule<>(new ClassCondition<>(PhysicDefinition.class, DataSignal.class), new Reaction<ClassContext<PhysicDefinition>>() {
        @Override
        public void accept(ClassContext<PhysicDefinition> physicReflectionClassContext) {
            PhysicDefinition physicDefinition = physicReflectionClassContext.getInstance();
            PhysicBody physicBody = physicDefinition.physicBody;
            DataSignal dataSignal = (DataSignal) physicReflectionClassContext.getSignal();
            switch (dataSignal) {
                case Add:
                    if (physicBody.isBodyCreated()) {
                        physicBody.destroyBody(world);
                    }
                    physicBody.createBody(world);
                    physicBody.createFixture();
                    break;
                case Deletion:
                    physicBody.reset();
            }
        }
    });

    private Rule physicReflectionModificationRule = new Rule(new ClassCondition<>(PhysicDefinition.class, PhysicReflectionSignal.class), new Reaction<ClassContext<PhysicDefinition>>() {
        @Override
        public void accept(ClassContext<PhysicDefinition> physicReflectionClassContext) {
            PhysicDefinition physicDefinition = physicReflectionClassContext.getInstance();
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
        systemPort.addDataInstance(physicDefinitionRule);
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
        systemPort.removeDataInstance(physicDefinitionRule);
        systemPort.removeDataInstance(physicReflectionModificationRule);
        world.dispose();
    }
}
