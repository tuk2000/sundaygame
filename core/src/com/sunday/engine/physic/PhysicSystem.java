package com.sunday.engine.physic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.contextbank.ContextBank;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.*;

import java.util.HashMap;
import java.util.Map;

public class PhysicSystem extends SubSystem implements DataProvider<PhysicBodyCondition>, Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;
    protected Map<PhysicBody, PhysicBodyContext> physicBodyToContextMap = new HashMap<>();
    protected ContextBank contextBank;

    private Rule<ClassContext<PhysicBodyContext>> physicReflectionModificationRule
            = new Rule<>(new ClassCondition<>(PhysicBody.class, CollisionSignal.class), new ClassReaction<PhysicBodyContext>() {
        @Override
        public void accept(PhysicBodyContext physicBodyContext) {
            PhysicBody physicBody = physicBodyContext.getData();
            CollisionSignal physicReflectionSignal = (CollisionSignal) physicBodyContext.getSignal();
            switch (physicReflectionSignal) {
                case None:
                case Updated:
            }
            System.out.println("PhysicSystem---" + physicBody + "---" + physicReflectionSignal.name());
        }
    });


    public PhysicSystem(SystemPort systemPort, ContextBank contextBank) {
        super("PhysicSystem", systemPort);
        world = new World(defaultGravity, false);
        this.contextBank = contextBank;
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
        systemPort.removeDataInstance(physicReflectionModificationRule);
        world.dispose();
    }

    @Override
    public boolean isSuitedFor(Condition condition) {
        return condition instanceof PhysicBodyCondition;
    }

    @Override
    public PhysicBody requestData(PhysicBodyCondition condition) {
        PhysicBody physicBody = condition.getData();
        if (physicBody.isBodyCreated()) {
            physicBody.destroyWithDefinition(world);
            physicBody.createWithDefinition(world);
        }
        if (!physicBody.isBodyCreated())
            physicBody.createWithDefinition(world);
        System.out.println("PhysicSystem----initialing PhysicCondition");
        return physicBody;
    }

    @Override
    public <D extends Data> void feedback(D data, DataContext<D> dataContext) {
        physicBodyToContextMap.put((PhysicBody) data, (PhysicBodyContext) dataContext);
    }
}
