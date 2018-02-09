package com.sunday.engine.common.viewlayers;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.ViewLayer;

public abstract class SingleComponentLayer<T extends Disposable> implements ViewLayer, Disposable {
    private boolean visible = true;
    private T viewComponent;

    protected void setViewComponent(T t) {
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
