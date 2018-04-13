package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.signal.DataSignal;
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

    private Rule<CustomizedDataContext<PhysicDefinition>> physicDefinitionRule = new Rule<CustomizedDataContext<PhysicDefinition>>(new ClassCondition(PhysicDefinition.class, DataSignal.class), new Reaction<CustomizedDataContext<PhysicDefinition>>() {
        @Override
        public void accept(CustomizedDataContext<PhysicDefinition> physicDefinitionCustomizedDataContext) {
            PhysicDefinition physicDefinition = physicDefinitionCustomizedDataContext.getData();
            PhysicBody physicBody = physicDefinition.physicBody;
            DataSignal dataSignal = (DataSignal) physicDefinitionCustomizedDataContext.getSignal();
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

    private Rule<CustomizedDataContext<PhysicDefinition>> physicReflectionModificationRule = new Rule<CustomizedDataContext<PhysicDefinition>>(new ClassCondition(PhysicDefinition.class, PhysicReflectionSignal.class), new Reaction<CustomizedDataContext<PhysicDefinition>>() {
        @Override
        public void accept(CustomizedDataContext<PhysicDefinition> physicDefinitionCustomizedDataContext) {
            PhysicDefinition physicDefinition = physicDefinitionCustomizedDataContext.getData();
            PhysicBody physicBody = physicDefinition.physicBody;
            PhysicReflectionSignal physicReflectionSignal = (PhysicReflectionSignal) physicDefinitionCustomizedDataContext.getSignal();
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
