package com.sunday.engine.scenario.physicprocess;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.PhysicReflection;
import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.condition.DataCondition;
import com.sunday.engine.rule.rules.TriggerRule;

public class PhysicSimulator extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    public World getWorld() {
        return world;
    }

    public PhysicSimulator(SystemPort systemPort) {
        super("PhysicSimulator", systemPort);
        world = new World(defaultGravity, false);
        systemPort.addDataInstance(new TriggerRule(condition, reaction));
    }

    private Condition condition = new DataCondition(PhysicReflection.class, DataSignal.Add, DataSignal.Modification, DataSignal.Deletion);
    private Reaction reaction = new Reaction() {

        @Override
        public void run() {

        }

//        @Override
//        public void execute(SynchronizeEvent synchronizeEvent) {
//            PhysicReflection physicReflection = (PhysicReflection) synchronizeEvent.source;
//            switch (synchronizeEvent.dataSignal) {
//                case Add:
//                    physicReflection.bodyCreated = true;
//                    physicReflection.body = world.createBody(physicReflection.bodyDef);
//                    physicReflection.createFixture();
//                    break;
//                case Modification:
//                    world.destroyBody(physicReflection.body);
//                    physicReflection.body = world.createBody(physicReflection.bodyDef);
//                    physicReflection.createFixture();
//                    break;
//                case Deletion:
//                    physicReflection.bodyCreated = false;
//                    world.destroyBody(physicReflection.body);
//                    break;
//            }
//        }
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
