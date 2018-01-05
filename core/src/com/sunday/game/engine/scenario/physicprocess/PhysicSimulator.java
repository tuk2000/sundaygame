package com.sunday.game.engine.scenario.physicprocess;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.common.PhysicReflection;
import com.sunday.game.engine.databank.DataBank;
import com.sunday.game.engine.databank.port.UserPort;
import com.sunday.game.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.game.engine.databank.synchronize.SynchronizeEvent;
import com.sunday.game.engine.databank.synchronize.SynchronizeExecutor;

public class PhysicSimulator implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;

    public World getWorld() {
        return world;
    }

    public PhysicSimulator(DataBank dataBank) {
        world = new World(defaultGravity, false);
        connectDataBank(dataBank.getUserPort(this, PhysicReflection.class));
    }

    private void connectDataBank(UserPort<PhysicReflection> dataUserPort) {
        dataUserPort.addDataSynchronize(synchronizeCondition, synchronizeExecutor);
    }

    private SynchronizeCondition synchronizeCondition = new SynchronizeCondition(PhysicReflection.class, DataOperation.Add, DataOperation.Modification, DataOperation.Deletion);
    private SynchronizeExecutor synchronizeExecutor = new SynchronizeExecutor() {
        @Override
        public void execute(SynchronizeEvent synchronizeEvent) {
            PhysicReflection physicReflection = (PhysicReflection) synchronizeEvent.source;
            switch (synchronizeEvent.dataOperation) {
                case Add:
                    physicReflection.bodyCreated = true;
                    physicReflection.body = world.createBody(physicReflection.bodyDef);
                    physicReflection.createFixture();
                    break;
                case Modification:
                    world.destroyBody(physicReflection.body);
                    physicReflection.body = world.createBody(physicReflection.bodyDef);
                    physicReflection.createFixture();
                    break;
                case Deletion:
                    physicReflection.bodyCreated = false;
                    world.destroyBody(physicReflection.body);
                    break;
            }
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
