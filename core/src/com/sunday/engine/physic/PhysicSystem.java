package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.ClassCondition;

public class PhysicSystem extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    private Rule physicReflectionAddRule = new Rule(new ClassCondition<>(PhysicReflection.class, DataSignal.Modification), new Reaction<SourceClass<PhysicReflection>>() {
        @Override
        public void accept(SourceClass<PhysicReflection> physicReflectionSourceClass) {
            PhysicReflection physicReflection = physicReflectionSourceClass.getSensedData();
            world.destroyBody(physicReflection.body);
            physicReflection.bodyCreated = true;
            physicReflection.body = world.createBody(physicReflection.bodyDef);
            physicReflection.createFixture();
        }
    });

    private Rule physicReflectionModificationRule = new Rule(new ClassCondition<>(PhysicReflection.class, DataSignal.Modification), new Reaction<SourceClass<PhysicReflection>>() {
        @Override
        public void accept(SourceClass<PhysicReflection> physicReflectionSourceClass) {
            PhysicReflection physicReflection = physicReflectionSourceClass.getSensedData();
            world.destroyBody(physicReflection.body);
            physicReflection.body = world.createBody(physicReflection.bodyDef);
            physicReflection.createFixture();
        }
    });

    private Rule physicReflectionDeletionRule = new Rule(new ClassCondition<>(PhysicReflection.class, DataSignal.Deletion), new Reaction<SourceClass<PhysicReflection>>() {
        @Override
        public void accept(SourceClass<PhysicReflection> physicReflectionSourceClass) {
            PhysicReflection physicReflection = physicReflectionSourceClass.getSensedData();
            physicReflection.bodyCreated = false;
            world.destroyBody(physicReflection.body);
        }
    });

    public PhysicSystem(SystemPort systemPort) {
        super("PhysicSystem", systemPort);
        world = new World(defaultGravity, false);
        systemPort.addDataInstance(physicReflectionAddRule);
        systemPort.addDataInstance(physicReflectionModificationRule);
        systemPort.addDataInstance(physicReflectionDeletionRule);
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
        world.dispose();
    }
}
