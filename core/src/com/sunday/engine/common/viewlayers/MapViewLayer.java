package com.sunday.engine.common.viewlayers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapViewLayer extends SingleComponentLayer<TiledMap> {

    public MapViewLayer(TiledMap map) {
        setViewComponent(map);
    }

    public MapViewLayer(String mapPath) {
        TiledMap  map = new TmxMapLoader().load(mapPath);
        setViewComponent(map);
    }

    @Override
    public Class getComponentClass() {
        return TiledMap.class;
    }
}
