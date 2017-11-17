package com.sunday.game.engine.view.views;

import com.sunday.game.engine.view.View;
import com.sunday.game.engine.view.viewlayers.PhysicViewLayer;
import com.sunday.game.engine.view.viewlayers.ScreenViewLayer;

public abstract class RoleView extends View {
    protected ScreenViewLayer screenViewLayer;
    protected PhysicViewLayer physicViewLayer;

    public RoleView() {
        super(false);
        screenViewLayer = new ScreenViewLayer();
        physicViewLayer = new PhysicViewLayer();
        addViewLayers(screenViewLayer, physicViewLayer);
    }
}
