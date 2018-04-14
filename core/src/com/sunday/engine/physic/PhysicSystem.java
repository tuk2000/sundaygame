package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.model.property.PhysicBody;
import com.sunday.engine.model.property.PhysicBodyContext;
import com.sunday.engine.model.property.PhysicDefinition;
import com.sunday.engine.model.property.PhysicReflectionSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.ClassReaction;
import com.sunday.engine.rule.Rule;

import java.util.HashMap;
import java.util.Map;

public class PhysicSystem extends SubSystem implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;
    protected Map<PhysicDefinition, PhysicBody> definitionToBodyMap = new HashMap<>();
    protected Map<PhysicBody, PhysicBodyContext> physicBodyToContextMap = new HashMap<>();

    private Rule<ClassContext<CustomizedDataContext<PhysicDefinition>>> physicDefinitionRule
            = new Rule<>(new ClassCondition<>(PhysicDefinition.class, DataSignal.class), new ClassReaction<CustomizedDataContext<PhysicDefinition>>() {
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
                    PhysicBodyContext physicBodyContext = new PhysicBodyContext(physicBody);
                    definitionToBodyMap.put(physicDefinition, physicBody);
                    physicBodyToContextMap.put(physicBody, physicBodyContext);
                    break;
                case Deletion:
                    physicBody.reset();
            }
            System.out.println("PhysicSystem----" + physicDefinition.owner + " Signal---" + dataSignal.name());
        }
    });

    private Rule<ClassContext<CustomizedDataContext<PhysicDefinition>>> physicReflectionModificationRule
            = new Rule<>(new ClassCondition<>(PhysicDefinition.class, PhysicReflectionSignal.class), new ClassReaction<CustomizedDataContext<PhysicDefinition>>() {
        @Override
        public void accept(CustomizedDataContext<PhysicDefinition> physicDefinitionCustomizedDataContext) {
            PhysicDefinition physicDefinition = physicDefinitionCustomizedDataContext.getData();
            PhysicBody physicBody = physicDefinition.physicBody;
            PhysicReflectionSignal physicReflectionSignal = (PhysicReflectionSignal) physicDefinitionCustomizedDataContext.getSignal();
            switch (physicReflectionSignal) {
                case None:
                case Updated:
            }
            System.out.println("PhysicSystem----" + physicDefinition.owner + " Signal---" + physicReflectionSignal.name());
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
