package com.sunday.engine.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.event.EventProcessor;
import com.sunday.engine.examples.Role;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.viewlayers.MapViewLayer;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.model.state.Label;
import com.sunday.engine.physic.PhysicSimulator;
import com.sunday.engine.render.independentrenders.*;
import com.sunday.engine.render.managers.CameraManager;
import com.sunday.engine.render.managers.DisplayManager;
import com.sunday.engine.render.managers.RendererManager;

import java.util.ArrayList;
import java.util.List;

public class ScenarioRenderer implements Disposable {

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

    private float viewportWidth; //units
    private float viewportHeight; //units

    private float worldWidth;//units
    private float worldHeight;//units

    private RendererManager rendererManager;
    private CameraManager cameraManager;
    private DisplayManager displayManager;

    public ScenarioRenderer(PhysicSimulator physicSimulator) {

        rendererManager = new RendererManager();

        // aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

        viewportHeight = 20.0f;
        viewportWidth = 20.0f;

        worldHeight = 800;
        worldWidth = 800;

        sharedBatch = new SpriteBatch();
        sharedCamera = new OrthographicCamera(viewportWidth, viewportHeight);
        cameraManager = new CameraManager(sharedCamera);
        //sharedCamera = new OrthographicCamera();
        screenViewport = new FitViewport(worldWidth, worldHeight, sharedCamera);
        screenViewport.apply();
        displayManager = new DisplayManager(cameraManager, screenViewport);

        sharedCamera.position.set(worldWidth / 2, worldHeight / 2, 0);
        sharedCamera.update();
        //screenViewport = new ScreenViewport(sharedCamera);
        spriteRender = new SpriteRenderer(sharedBatch);
//        stageRender = new StageRenderer(sharedBatch, screenViewport);
        stageRender = new StageRenderer();
        textureRender = new TextureRenderer(sharedBatch);
        worldRender = new WorldRenderer(sharedCamera);
        worldRender.combineWithWorld(physicSimulator.getWorld());
    }

    public List<EventProcessor> getProcessors() {
        List<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(cameraManager);
        eventProcessors.add(rendererManager);
        eventProcessors.add(displayManager);
        return eventProcessors;
    }

    public void readyToRenderRole(Role role) {
        switch (role.label) {
            case Map:
                renderMap(role);
                break;
            case Hero:
            case Enemy:
                renderSingleRole(role);
        }
    }

    private void renderMap(Role role) {
        if (role.label != Label.Map) return;
        role.abstractModel.outlook.viewLayers.forEach(e -> {
            if (e instanceof MapViewLayer) {
                renderMapViewLayer((MapViewLayer) e);
            }
        });
    }


    private void renderSingleRole(Role role) {
        if (role.label == Label.Map) return;
        role.abstractModel.outlook.viewLayers.forEach(e -> {
            if (e instanceof TextureViewLayer) {
                renderScreenViewLayer((TextureViewLayer) e, role.abstractModel);
            }
        });
    }

    private void renderScreenViewLayer(TextureViewLayer e, AbstractModel model) {
        Texture component = (Texture) e.getViewComponent();
        if (component == null) return;
        if (component instanceof Texture) {
            Vector2 position = model.movement.position;
            Vector2 dimension = model.outlook.dimension;
            textureRender.renderLater((Texture) component, position.x, position.y, dimension.x, dimension.y);
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
        AnimationTimer.synchronize();

        //sharedCamera.translate(-10.0f * delta, 0);
        //sharedCamera.rotate(10 * delta);
        sharedCamera.update();
        sharedBatch.setProjectionMatrix(sharedCamera.combined);

        if (rendererManager.DoRenderMap & mapRender != null) {
            mapRender.updateCamera(sharedCamera);
            mapRender.render(delta);
        }
        if (rendererManager.DoRenderSprite)
            spriteRender.render(delta);
        if (rendererManager.DoRenderTexture)
            textureRender.render(delta);
        if (rendererManager.DoRenderStage)
            stageRender.render(delta);
        if (rendererManager.DoRenderWorld)
            worldRender.render(delta);
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
