package com.sunday.game.World.Senario;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

class ScenarioRenderer {

    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer box2DDebugRenderer;

    private float lastWorldRenderDuration = 0.0f;
    private World world;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;
    private Stage stage;

    public ScenarioRenderer(Stage stage) {
        this.stage = stage;
        spriteBatch = new SpriteBatch();
    }

    public void renderMap(TiledMap tiledMap) {
        if (mapRenderer == null || !mapRenderer.getMap().equals(tiledMap)) {
            mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        }
        mapRenderer.render();
    }

    public void renderSprite(Sprite sprite) {
        spriteBatch.begin();
        spriteBatch.draw(sprite.getTexture(), sprite.getX(), sprite.getY());
        spriteBatch.end();
    }

    public void renderPhysicBody(BodyDef bodyDef, FixtureDef fixtureDef) {
        if (box2DDebugRenderer == null || world == null)
            return;
        world.createBody(bodyDef).createFixture(fixtureDef);
    }

    public void initPhysicLayer(World world, Camera camera) {
        box2DDebugRenderer = new Box2DDebugRenderer();
        this.world = world;
        box2DDebugRenderer.render(world, camera.combined);
    }

    public void renderWorldStep() {
        if (world == null) return;
        if (lastWorldRenderDuration > 1 / 60) {
            lastWorldRenderDuration -= 1 / 60;
            world.step(1 / 60, 8, 3);
        }
    }

    public void render(float delta) {
        lastWorldRenderDuration += delta;
        renderWorldStep();
        stage.draw();
    }

}
