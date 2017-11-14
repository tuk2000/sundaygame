package com.sunday.game.World.Senario;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.game.World.View.Animation.AnimationSetting;
import com.sunday.game.World.View.EntityPhysicDefinition;
import com.sunday.game.World.View.ViewLayers.MultiComponentLayer;
import com.sunday.game.World.View.ViewLayers.SingleComponentLayer;

import java.util.ArrayList;

public class ScenarioRenderer implements Disposable {
    private GameScenarioEngine gameScenarioEngine;

    private OrthogonalTiledMapRenderer mapRenderer;

    private Box2DDebugRenderer box2DDebugRenderer;//in Box2DDebugRenderer class there is ShapeRender
    private float lastWorldRenderDuration = 0.0f;
    private ArrayList<EntityPhysicDefinition> entityPhysicDefinitions = new ArrayList<>();
    private World world;
    private ShapeRenderer shapeRenderer;

    private Stage stage;
    private Batch batch;
    private Camera camera;
    private Viewport viewPort;

    public ScenarioRenderer(Stage stage, GameScenarioEngine gameScenarioEngine) {
        this.stage = stage;
        batch = stage.getBatch();//use only the Batch from stage
        viewPort = stage.getViewport();
        camera = stage.getCamera();

        this.gameScenarioEngine = gameScenarioEngine;
        world = new World(new Vector2(0.0f, -9.8f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    public void renderMap(TiledMap tiledMap) {
        if (mapRenderer == null || mapRenderer.getMap() != tiledMap) {
            mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        }
        if (shouldRender()) {
            mapRenderer.setView((OrthographicCamera) camera);
            mapRenderer.render();
        }
    }

    private boolean shouldRender() {
        if (lastWorldRenderDuration > 1 / 60) {
            lastWorldRenderDuration -= 1 / 60;
            return true;
        } else
            return false;
    }

    public void renderTexture(Texture texture) {
        batch.begin();
        batch.draw(texture, 10.0f, 10.0f);
        batch.end();
    }

    public void renderSprite(Sprite sprite) {
        batch.begin();
        batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY());
        batch.end();
    }

    public void renderPhysicBody(BodyDef bodyDef, FixtureDef fixtureDef) {
        world.createBody(bodyDef).createFixture(fixtureDef);
    }

    public void renderWorldStep() {
        if (world == null) return;
        if (shouldRender()) {
            world.step(1 / 60, 8, 3);
            box2DDebugRenderer.render(world, camera.combined);
        }
    }

    private void renderComponent(Object component) {
        if (component instanceof Texture) {
            renderTexture((Texture) component);
        } else if (component instanceof Sprite) {
            renderSprite((Sprite) component);
        } else if (component instanceof TiledMap) {
            renderMap((TiledMap) component);
        } else if (component instanceof EntityPhysicDefinition) {
            EntityPhysicDefinition entityPhysicDefinition = (EntityPhysicDefinition) component;
            if (!entityPhysicDefinitions.contains(entityPhysicDefinition)) {
                renderPhysicBody(entityPhysicDefinition.bodyDef, entityPhysicDefinition.fixtureDef);
                entityPhysicDefinitions.add(entityPhysicDefinition);
            }

        }
    }

    private void renderView(Role role) {
        if (!role.view.isStatic()) {
            role.view.updateView();
        }
        role.view.getViewLayers().forEach(vl -> {
            if (vl instanceof MultiComponentLayer) {
                ((MultiComponentLayer) vl).getViewComponents().forEach(this::renderComponent);
            } else {
                renderComponent(((SingleComponentLayer) vl).getViewComponent());
            }
        });
    }

    public void render(float delta) {
        AnimationSetting.DeltaTime += delta;
        lastWorldRenderDuration += delta;
        renderWorldStep();
        gameScenarioEngine.getRootScenario().getRoles().forEach(this::renderView);
        stage.draw();
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        box2DDebugRenderer.dispose();
        world.dispose();
        shapeRenderer.dispose();
        stage.dispose();
        batch.dispose();
    }
}
