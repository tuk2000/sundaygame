package com.sunday.game.engine.view;

import com.sunday.game.engine.view.viewlayers.ScreenViewLayer;

public abstract class RoleAbstractView extends AbstractView {
    protected ScreenViewLayer screenViewLayer;

    public RoleAbstractView() {
        screenViewLayer = new ScreenViewLayer();
        addViewLayers(screenViewLayer);
    }
}
