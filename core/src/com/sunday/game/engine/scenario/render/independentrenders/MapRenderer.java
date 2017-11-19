package com.sunday.game.engine.scenario.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sunday.game.engine.scenario.render.IndependentRenderer;

public class MapRenderer implements IndependentRenderer {
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapRenderer(Batch batch, Camera camera, TiledMap map) {
        mapRenderer = new OrthogonalTiledMapRenderer(map, batch);
        mapRenderer.setView((OrthographicCamera) camera);
    }

    public void updateCamera(Camera camera) {
        mapRenderer.setView((OrthographicCamera) camera);
    }

    @Override
    public void render(float delta) {
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
    }


}
