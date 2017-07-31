package com.sunday.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class Player implements ApplicationListener {
    SpriteBatch batch;
    Texture player;
    TextureRegion[][] tRegions;
    Sprite sprite;
    int frame=0,zeile = 0;

    @Override
    public void create() {
        player = new Texture("playerSp.png");
        batch = new SpriteBatch();
        tRegions = TextureRegion.split(player,275,385);
        sprite = new Sprite(tRegions[0][0]);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                frame++;
                if (frame>4){
                    frame = 0;
                    if (zeile==1){
                        zeile = 0;
                    }else {
                        zeile = 1;
                    }
                }
                sprite.setRegion(tRegions[zeile][frame]);
            }
        },0,1/15f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
