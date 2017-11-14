package com.sunday.game.World.View.ViewLayers;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class MapViewLayer extends MultiComponentLayer<TiledMap> {

    public MapViewLayer(TiledMap map) {
        addToViewComponents(map);
    }

    @Override
    public Class getComponentClass() {
        return TiledMap.class;
    }
}
