package com.sunday.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;

public class Game implements ApplicationListener{
     SpriteBatch batch;
     BitmapFont font;
     ShapeRenderer shapeRenderer;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(20,20,20,20);
        shapeRenderer.rect(0,0,800,20,Color.BLACK,Color.GRAY,Color.DARK_GRAY,Color.LIGHT_GRAY);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch,"Hello World",200,200);
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
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }
}
