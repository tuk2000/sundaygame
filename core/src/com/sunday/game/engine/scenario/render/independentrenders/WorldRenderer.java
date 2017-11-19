package com.sunday.game.engine.scenario.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sunday.game.engine.common.EntityPhysicDefinition;
import com.sunday.game.engine.scenario.render.IndependentRenderer;

import java.util.ArrayList;
import java.util.Collections;

public class WorldRenderer implements IndependentRenderer {
    private World world;
    private Camera camera;
    private Box2DDebugRenderer box2DDebugRenderer;//in Box2DDebugRenderer class there is ShapeRenderer
    private ArrayList<EntityPhysicDefinition> entityPhysicDefinitions;


    public WorldRenderer(Camera camera) {
        world = new World(new Vector2(0.0f, -9.8f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        entityPhysicDefinitions = new ArrayList<>();
        entityPhysicDefinitions = new ArrayList<>();
        this.camera = camera;
        box2DDebugRenderer.render(world, camera.combined);
    }

    public void addEntityPhysicDefinition(EntityPhysicDefinition... entityPhysicDefinitions) {
        Collections.addAll(this.entityPhysicDefinitions, entityPhysicDefinitions);
        this.entityPhysicDefinitions.forEach(e -> {
            world.createBody(e.bodyDef).createFixture(e.fixtureDef);
        });
    }

    public boolean hasEntityPhysicDefinition(EntityPhysicDefinition entityPhysicDefinition) {
        return entityPhysicDefinitions.contains(entityPhysicDefinition);
    }

    public void worldStep() {
        world.step(1 / 60f, 8, 3);
    }

    @Override
    public void render(float delta) {
        camera.update();
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
