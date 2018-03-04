package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicReflection;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.DataCondition;

public class PhysicSimulator extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    public World getWorld() {
        return world;
    }

    public PhysicSimulator(SystemPort systemPort) {
        super("PhysicSimulator", systemPort);
        world = new World(defaultGravity, false);
        systemPort.addDataInstance(new Rule(addCondition, addReaction));
        systemPort.addDataInstance(new Rule(modificationCondition, modificationReaction));
        systemPort.addDataInstance(new Rule(deletionCondition, deletionReaction));
    }

    private Condition addCondition = DataCondition.classSignals(PhysicReflection.class, DataSignal.Add);
    private Condition modificationCondition = DataCondition.classSignals(PhysicReflection.class, DataSignal.Modification);
    private Condition deletionCondition = DataCondition.classSignals(PhysicReflection.class, DataSignal.Deletion);
    private Reaction addReaction = new Reaction() {

        @Override
        public void run() {
//                    physicReflection.bodyCreated = true;
//                    physicReflection.body = world.createBody(physicReflection.bodyDef);
//                    physicReflection.createFixture();
//                    break;
        }
    };

    private Reaction modificationReaction = new Reaction() {
        @Override
        public void run() {
//                    world.destroyBody(physicReflection.body);
//                    physicReflection.body = world.createBody(physicReflection.bodyDef);
//                    physicReflection.createFixture();
//                    break;
        }
    };

    private Reaction deletionReaction = new Reaction() {

        @Override
        public void run() {
//                    physicReflection.bodyCreated = false;
//                    world.destroyBody(physicReflection.body);
//                    break;
        }

    };

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
