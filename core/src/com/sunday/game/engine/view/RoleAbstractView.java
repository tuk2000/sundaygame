package com.sunday.game.engine.view;

import com.sunday.game.engine.view.viewlayers.PhysicViewLayer;
import com.sunday.game.engine.view.viewlayers.ScreenViewLayer;

public abstract class RoleAbstractView extends AbstractView {
    protected ScreenViewLayer screenViewLayer;
    protected PhysicViewLayer physicViewLayer;

    public RoleAbstractView() {
        screenViewLayer = new ScreenViewLayer();
        physicViewLayer = new PhysicViewLayer();
        addViewLayers(screenViewLayer, physicViewLayer);
    }
}
