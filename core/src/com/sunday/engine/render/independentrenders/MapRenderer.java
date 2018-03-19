package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.IndependentRenderer;

public class MapRenderer extends IndependentRenderer {
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private Camera camera;
    private Batch batch;
    private boolean ownBatch;

    public MapRenderer(Batch batch, Camera camera) {
        this.batch = batch;
        this.camera = camera;
        ownBatch = false;
    }

    private void adjustMapRender() {
        if (map != null) {
            if (mapRenderer != null)
                mapRenderer.dispose();
            mapRenderer = new OrthogonalTiledMapRenderer(map, batch);
            mapRenderer.setView((OrthographicCamera) camera);
        }
    }

    public void setMap(TiledMap map) {
        if (map.equals(this.map)) return;
        this.map = map;
        adjustMapRender();
    }

    @Override
    public void setCamera(Camera camera) {
        if (camera.equals(camera)) return;
        this.camera = camera;
        adjustMapRender();
    }

    @Override
    protected void renderInternal(float delta) {
        if (map == null) {
            return;
        }
        mapRenderer.render();
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void dispose() {
        if (ownBatch)
            batch.dispose();
        mapRenderer.dispose();
    }


}
