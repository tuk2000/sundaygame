package com.sunday.game.World.View.Views;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.sunday.game.World.View.View;
import com.sunday.game.World.View.ViewLayers.MapViewLayer;
import com.sunday.game.World.View.ViewLayers.PhysicViewLayer;

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
