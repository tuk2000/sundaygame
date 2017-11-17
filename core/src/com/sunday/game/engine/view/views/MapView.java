package com.sunday.game.engine.view.views;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.sunday.game.engine.view.View;
import com.sunday.game.engine.view.viewlayers.MapViewLayer;
import com.sunday.game.engine.view.viewlayers.PhysicViewLayer;

public class MapView extends View {
    private MapViewLayer mapViewLayer;
    private PhysicViewLayer physicViewLayer;
    private TiledMap map;

    public MapView() {
        super(true);
        map = new TmxMapLoader().load("TileMap/sTest/sTest.tmx");
        mapViewLayer = new MapViewLayer(map);
        physicViewLayer = new PhysicViewLayer();
        addViewLayers(mapViewLayer, physicViewLayer);
    }

    @Override
    public void updateLayers() {

    }

    @Override
    public void dispose() {
        map.dispose();
    }
}
