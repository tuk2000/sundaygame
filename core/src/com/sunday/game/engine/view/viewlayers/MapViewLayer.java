package com.sunday.game.engine.view.viewlayers;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapViewLayer extends SingleComponentLayer<TiledMap> {

    public MapViewLayer(TiledMap map) {
        setViewComponent(map);
    }

    @Override
    public Class getComponentClass() {
        return TiledMap.class;
    }
}
