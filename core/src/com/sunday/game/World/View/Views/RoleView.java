package com.sunday.game.World.View.Views;

import com.sunday.game.World.View.View;
import com.sunday.game.World.View.ViewLayers.PhysicViewLayer;
import com.sunday.game.World.View.ViewLayers.ScreenViewLayer;

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
