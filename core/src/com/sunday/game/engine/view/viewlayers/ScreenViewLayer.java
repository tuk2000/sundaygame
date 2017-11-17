package com.sunday.game.engine.view.viewlayers;

import com.badlogic.gdx.graphics.Texture;

public class ScreenViewLayer<T extends Texture> extends SingleComponentLayer<T> {
    @Override
    public Class getComponentClass() {
        return Texture.class;
    }

}
