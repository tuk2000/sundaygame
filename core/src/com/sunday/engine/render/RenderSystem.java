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
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.examples.Label;
import com.sunday.engine.examples.Role;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.viewlayers.MapViewLayer;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.physic.PhysicSystem;
import com.sunday.engine.render.independentrenders.*;
import com.sunday.engine.render.managers.CameraManager;
import com.sunday.engine.render.managers.DisplayManager;
import com.sunday.engine.render.managers.RendererManager;


public class RenderSystem extends SubSystem implements Disposable {
    private DebugRenderer debugRenderer;
    private MapRenderer mapRender;
    private SpriteRenderer spriteRender;
    private StageRenderer stageRender;
    private TextureRenderer textureRender;
    private WorldRenderer worldRender;

    private OrthographicCamera sharedCamera;
    //    private FitViewport screenViewport;
//    private ScreenViewport screenViewport;
    private Viewport screenViewport;
    private Batch sharedBatch;

    private float viewportWidth; //units
    private float viewportHeight; //units

    private float worldWidth;//units
    private float worldHeight;//units

    private RendererManager rendererManager;
    private CameraManager cameraManager;
    private DisplayManager displayManager;

    public RenderSystem(SystemPort systemPort) {
        super("RenderSystem", systemPort);

        rendererManager = new RendererManager();
        rendererManager.connectWith(systemPort);

//         aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

        viewportHeight = 100;
        viewportWidth = 100;

        worldHeight = 1000;
        worldWidth = 1000;

        sharedBatch = new SpriteBatch();
        sharedCamera = new OrthographicCamera(viewportWidth, viewportHeight);
        cameraManager = new CameraManager(sharedCamera);
        cameraManager.connectWith(systemPort);
//        sharedCamera = new OrthographicCamera();
        screenViewport = new FitViewport(worldWidth, worldHeight, sharedCamera);
        screenViewport.apply(true);
        displayManager = new DisplayManager(cameraManager, screenViewport);
        displayManager.connectWith(systemPort);

        mapRender = new MapRenderer(sharedBatch, sharedCamera);
//        screenViewport = new ScreenViewport(sharedCamera);
        spriteRender = new SpriteRenderer(sharedBatch, sharedCamera);
        spriteRender.connectWith(systemPort);
//        stageRender = new StageRenderer(sharedBatch, screenViewport);
        stageRender = new StageRenderer();
        textureRender = new TextureRenderer(sharedBatch, sharedCamera);
        textureRender.connectWith(systemPort);
        worldRender = new WorldRenderer(sharedCamera);
        worldRender.connectWith(systemPort);

        debugRenderer = new DebugRenderer();
        debugRenderer.setCamera(sharedCamera);
        debugRenderer.connectWith(systemPort);
    }

    public void setPhysicSystem(PhysicSystem physicSystem) {
        worldRender.combineWithWorld(physicSystem.getWorld());
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
            mapRender.setMap((TiledMap) component);
        }
    }

    public void render(float delta) {
        AnimationSetting.DeltaTime += delta;
        AnimationTimer.synchronize();

        cameraManager.updateCamera();
        sharedBatch.setProjectionMatrix(sharedCamera.combined);

        mapRender.setWorking(rendererManager.DoRenderMap);
        mapRender.render(delta);

        spriteRender.setWorking(rendererManager.DoRenderSprite);
        spriteRender.render(delta);

        textureRender.setWorking(rendererManager.DoRenderTexture);
        textureRender.render(delta);

        stageRender.setWorking(rendererManager.DoRenderStage);
        stageRender.render(delta);

        worldRender.setWorking(rendererManager.DoRenderWorld);
        worldRender.render(delta);

        debugRenderer.setWorking(rendererManager.DoRenderDebug);
        debugRenderer.render(delta);
    }

    @Override
    public void dispose() {
        rendererManager.disconnectWith(systemPort);
        cameraManager.disconnectWith(systemPort);
        displayManager.disconnectWith(systemPort);

        mapRender.disconnectWith(systemPort);
        spriteRender.disconnectWith(systemPort);
        worldRender.disconnectWith(systemPort);
        stageRender.disconnectWith(systemPort);
        textureRender.disconnectWith(systemPort);
        debugRenderer.disconnectWith(systemPort);

        mapRender.dispose();
        spriteRender.dispose();
        worldRender.dispose();
        stageRender.dispose();
        textureRender.dispose();
        debugRenderer.dispose();
        sharedBatch.dispose();
    }
}
