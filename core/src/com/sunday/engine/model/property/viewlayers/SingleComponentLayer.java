package com.sunday.engine.model.property.viewlayers;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.model.property.ViewLayer;

public abstract class SingleComponentLayer<T extends Disposable> implements ViewLayer, Disposable {
    private boolean visible = true;
    private T viewComponent;

    public T getViewComponent() {
        if (visible)
            return viewComponent;
        else return null;
    }

    protected void setViewComponent(T t) {
        viewComponent = t;
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
