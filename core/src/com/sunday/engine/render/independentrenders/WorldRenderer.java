package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.IndependentRenderer;

public class WorldRenderer extends IndependentRenderer {
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
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    protected void renderInternal(float delta) {
        camera.update();
        if (world == null) return;
        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void dispose() {
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
