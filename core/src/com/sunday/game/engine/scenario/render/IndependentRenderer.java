package com.sunday.game.engine.scenario.render;

import com.badlogic.gdx.utils.Disposable;

public interface IndependentRenderer extends Disposable {
    void render(float delta);
}
