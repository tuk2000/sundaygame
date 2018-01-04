package com.sunday.game.engine.scenario.physicprocess;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.PhysicReflection;
import com.sunday.game.engine.databank.DataEventListener;
import com.sunday.game.engine.databank.DataUserPort;

import java.util.ArrayList;
import java.util.List;

public class PhysicSimulator implements Disposable {
    protected Vector2 defaultGravity = new Vector2(0, -9.8f);
    protected World world;
    private List<PhysicReflection> physicReflections;

    private DataEventListener<PhysicReflection> dataEventListener = new DataEventListener<PhysicReflection>() {
        @Override
        public void DataModified(List<PhysicReflection> list) {
            list.forEach(physicReflection -> {
                world.destroyBody(physicReflection.body);
                physicReflection.body = world.createBody(physicReflection.bodyDef);
            });
        }

        @Override
        public void DataDeleted(List<PhysicReflection> list) {
            list.forEach(physicReflection -> {
                physicReflection.bodyCreated = false;
                world.destroyBody(physicReflection.body);
            });
        }

        @Override
        public void DataAdded(List<PhysicReflection> list) {
            list.forEach(physicReflection -> {
                physicReflection.bodyCreated = true;
                physicReflection.body = world.createBody(physicReflection.bodyDef);
            });
        }
    };

    public World getWorld() {
        return world;
    }

    public PhysicSimulator() {
        world = new World(defaultGravity, false);
        physicReflections = new ArrayList<>();
    }

    public void readFromDataBank(DataUserPort<PhysicReflection> dataUserPort) {
        physicReflections = dataUserPort.getInstances(PhysicReflection.class);
        dataUserPort.registerDataEventListener(PhysicReflection.class, dataEventListener);
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
