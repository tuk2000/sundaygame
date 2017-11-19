package com.sunday.game.engine.scenario.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.game.engine.common.AnimationSetting;
import com.sunday.game.engine.common.EntityPhysicDefinition;
import com.sunday.game.engine.common.Role;
import com.sunday.game.engine.common.RoleLabel;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.scenario.ScenarioEngine;
import com.sunday.game.engine.scenario.render.independentrenders.*;
import com.sunday.game.engine.scenario.render.managers.CameraManager;
import com.sunday.game.engine.scenario.render.managers.ViewportManager;
import com.sunday.game.engine.view.viewlayers.MapViewLayer;
import com.sunday.game.engine.view.viewlayers.PhysicViewLayer;
import com.sunday.game.engine.view.viewlayers.ScreenViewLayer;

public class ScenarioRenderer implements Disposable {
    private ScenarioEngine scenarioEngine;
    private float lastRenderDuration = 0.0f;

    private MapRenderer mapRender;
    private SpriteRenderer spriteRender;
    private StageRenderer stageRender;
    private TextureRenderer textureRender;
    private WorldRenderer worldRender;

    private OrthographicCamera sharedCamera;
    //  private FitViewport screenViewport;
    //private ScreenViewport screenViewport;
    private Viewport screenViewport;
    private Batch sharedBatch;

    private float aspectRatio;//height/width
    private int displayWidth; // pixels
    private int displayHeight; // pixels

    private float viewportWidth; //units
    private float viewportHeight; //units

    private float worldWidth;//units
    private float worldHeight;//units

    public void resizeDisplay(int displayWidth, int displayHeight) {
        this.displayWidth = displayHeight;
        this.displayHeight = displayHeight;
        screenViewport.update(displayWidth, displayHeight);
        sharedCamera.position.set(worldWidth / 2, worldHeight / 2, 0);
    }


    private ViewportManager viewportManager;
    private CameraManager cameraManager;

    public ScenarioRenderer(ScenarioEngine scenarioEngine) {
        this.scenarioEngine = scenarioEngine;

        viewportManager = new ViewportManager();
        cameraManager = new CameraManager();

        // aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

        viewportHeight = 20.0f;
        viewportWidth = 20.0f;

        worldHeight = 800;
        worldWidth = 800;

        sharedBatch = new SpriteBatch();
        sharedCamera = new OrthographicCamera(viewportWidth, viewportHeight);
        //sharedCamera = new OrthographicCamera();
        screenViewport = new FitViewport(worldWidth, worldHeight, sharedCamera);
        screenViewport.apply();
        sharedCamera.position.set(worldWidth / 2, worldHeight / 2, 0);
        sharedCamera.update();
        //screenViewport = new ScreenViewport(sharedCamera);
        spriteRender = new SpriteRenderer(sharedBatch);
//        stageRender = new StageRenderer(sharedBatch, screenViewport);
        stageRender = new StageRenderer();
        textureRender = new TextureRenderer(sharedBatch);
        worldRender = new WorldRenderer(sharedCamera);
    }

    private void renderRole(Role role) {
        switch (role.roleLabel) {
            case Map:
                renderMap(role);
                break;
            case Hero:
            case Enemy:
                renderSingleRole(role);
        }
    }

    private void renderMap(Role role) {
        if (role.roleLabel != RoleLabel.Map) return;
        role.abstractView.getViewLayers().forEach(e -> {
            if (e instanceof MapViewLayer) {
                renderMapViewLayer((MapViewLayer) e);
            } else if (e instanceof PhysicViewLayer) {
                renderPhysicViewLayer((PhysicViewLayer) e);
            }
        });
    }


    private void renderSingleRole(Role role) {
        if (role.roleLabel == RoleLabel.Map) return;
        role.synchronize();
        role.abstractView.getViewLayers().forEach(e -> {
            if (e instanceof ScreenViewLayer) {
                renderScreenViewLayer((ScreenViewLayer) e, role.abstractModel);
            } else if (e instanceof PhysicViewLayer) {
                renderPhysicViewLayer((PhysicViewLayer) e);
            }
        });
    }

    private void renderScreenViewLayer(ScreenViewLayer e, AbstractModel model) {
        Texture component = (Texture) e.getViewComponent();
        if (component == null) return;
        if (component instanceof Texture) {
            Vector2 position = model.roleMovementStatus.position;
            Vector2 dimension = model.rolePresent.dimension;
            textureRender.renderLater((Texture) component, position.x, position.y, dimension.x, dimension.y);
        }
//        else if (component instanceof Sprite) {
//            spriteRender.renderLater((Sprite) component);
//        }
    }

    private void renderPhysicViewLayer(PhysicViewLayer e) {
        EntityPhysicDefinition entityPhysicDefinition = e.getViewComponent();
        if (entityPhysicDefinition == null) return;
        if (!worldRender.hasEntityPhysicDefinition(entityPhysicDefinition)) {
            worldRender.addEntityPhysicDefinition(entityPhysicDefinition);
        }
    }

    private void renderMapViewLayer(MapViewLayer e) {
        Object component = e.getViewComponent();
        if (component == null) return;
        if (component instanceof TiledMap) {
            if (mapRender == null) {
                mapRender = new MapRenderer(sharedBatch, sharedCamera, (TiledMap) component);
            }
        }
    }

    public void render(float delta) {
        AnimationSetting.DeltaTime += delta;
        lastRenderDuration += delta;
        worldRender.worldStep();

        //sharedCamera.translate(-10.0f * delta, 0);
        //sharedCamera.rotate(10 * delta);
        sharedCamera.update();
        sharedBatch.setProjectionMatrix(sharedCamera.combined);

        scenarioEngine.getRootScenario().getRoles().forEach(this::renderRole);

        worldRender.render(delta);
        if (mapRender != null) {
            mapRender.updateCamera(sharedCamera);
            mapRender.render(delta);
        }
        spriteRender.render(delta);
        stageRender.render(delta);
        textureRender.render(delta);
    }

    @Override
    public void dispose() {
        mapRender.dispose();
        spriteRender.dispose();
        worldRender.dispose();
        stageRender.dispose();
        textureRender.dispose();
    }
}
