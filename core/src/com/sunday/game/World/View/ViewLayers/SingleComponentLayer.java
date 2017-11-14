package com.sunday.game.World.View.ViewLayers;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.View.ViewLayer;

public abstract class SingleComponentLayer<T extends Disposable> implements ViewLayer, Disposable {
    private boolean visible = true;
    private T viewComponent;

    public void setViewComponent(T t) {
        viewComponent = t;
    }

    public T getViewComponent() {
        if (visible)
            return viewComponent;
        else return null;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void dispose() {
        viewComponent = null;
    }
}
