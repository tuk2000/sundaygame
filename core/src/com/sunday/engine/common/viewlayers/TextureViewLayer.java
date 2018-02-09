package com.sunday.engine.common.viewlayers;

import com.badlogic.gdx.graphics.Texture;

public class TextureViewLayer<T extends Texture> extends SingleComponentLayer<T> {
    private T t;

    public TextureViewLayer(T t) {
        this.t = t;
        setViewComponent(t);
    }

    public void updateTexture(T t) {
        if (this.t.equals(t)) return;
        else {
            this.t = t;
            setViewComponent(t);
        }
    }

    @Override
    public Class getComponentClass() {
        return t.getClass();
    }

}
