package com.sunday.engine.model.property.viewlayers;

import com.badlogic.gdx.graphics.Texture;

public class AnimationViewLayer extends SingleComponentLayer {
    @Override
    public Class getComponentClass() {
        return Texture.class;
    }
}
