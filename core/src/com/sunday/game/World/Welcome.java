package com.sunday.game.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Welcome extends Game {
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GamePlay(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
