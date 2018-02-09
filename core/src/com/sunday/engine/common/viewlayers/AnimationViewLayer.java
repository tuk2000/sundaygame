package com.sunday.engine.common.viewlayers;

import com.badlogic.gdx.graphics.Texture;

public class AnimationViewLayer extends SingleComponentLayer {
    @Override
    public Class getComponentClass() {
        return Texture.class;
    }
}
