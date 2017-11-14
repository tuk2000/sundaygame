package com.sunday.game.World.View.ViewLayers;

import com.badlogic.gdx.graphics.Texture;

public class ScreenViewLayer<T extends Texture> extends SingleComponentLayer<T> {
    @Override
    public Class getComponentClass() {
        return Texture.class;
    }

}
