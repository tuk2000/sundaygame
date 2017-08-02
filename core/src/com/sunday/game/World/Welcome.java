package com.sunday.game.World;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Welcome extends Game implements InputReciver {
   //SpriteBatch batch;
    GamePlay gamePlay;

    public Welcome() {
        gamePlay = new GamePlay(this);
    }

    @Override
    public void create() {
        //batch = new SpriteBatch();
        setScreen(gamePlay);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public InputAdapter getInputAdapter() {
        return gamePlay.getInputAdapter();
    }
}
