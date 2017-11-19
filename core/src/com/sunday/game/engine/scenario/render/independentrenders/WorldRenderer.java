package com.sunday.game.engine.scenario.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sunday.game.engine.scenario.render.IndependentRenderer;

public class WorldRenderer implements IndependentRenderer {
    private World world;
    private Camera camera;
    private Box2DDebugRenderer box2DDebugRenderer;//in Box2DDebugRenderer class there is ShapeRenderer

    public WorldRenderer(Camera camera) {
        box2DDebugRenderer = new Box2DDebugRenderer();
        this.camera = camera;
    }

    public void combineWithWorld(World world) {
        this.world = world;
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void render(float delta) {
        camera.update();
        if (world == null) return;
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
