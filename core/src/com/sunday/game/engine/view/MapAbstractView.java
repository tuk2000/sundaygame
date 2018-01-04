package com.sunday.game.engine.view;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.viewlayers.MapViewLayer;
import com.sunday.game.engine.view.viewlayers.PhysicViewLayer;

public class MapAbstractView extends AbstractView {
    protected PhysicViewLayer physicViewLayer;
    protected MapViewLayer mapViewLayer;
    private TiledMap map;

    public MapAbstractView(String fileName) {
        map = new TmxMapLoader().load(fileName);
        physicViewLayer = new PhysicViewLayer();
        mapViewLayer = new MapViewLayer(map);
        addViewLayers(mapViewLayer, physicViewLayer);
    }

    @Override
    public void dispose() {
        map.dispose();
    }

    @Override
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        physicViewLayer.setViewComponent(abstractModel.physicReflection);
        mapViewLayer.setViewComponent(map);
    }
}
