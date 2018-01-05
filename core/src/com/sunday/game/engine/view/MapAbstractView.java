package com.sunday.game.engine.view;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.sunday.game.engine.view.viewlayers.MapViewLayer;

public class MapAbstractView extends AbstractView {
    protected MapViewLayer mapViewLayer;
    private TiledMap map;

    public MapAbstractView(String fileName) {
        map = new TmxMapLoader().load(fileName);
        mapViewLayer = new MapViewLayer(map);
        addViewLayers(mapViewLayer);
    }

    @Override
    public void dispose() {
        map.dispose();
    }

}
