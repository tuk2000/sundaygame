package com.sunday.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;

public class Game implements ApplicationListener{
    OrthographicCamera camera;
     SpriteBatch batch;
     Texture texture;
     BitmapFont font;
     Sprite sprite;
     ShapeRenderer shapeRenderer;
    @Override
    public void create() {
        texture = new Texture(Gdx.files.internal("player_img/player1.png"));
        batch = new SpriteBatch();
        sprite = new Sprite(texture);
        sprite.setSize(75,75);
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
        font.draw(batch,"Sunday Game",200,200);
        batch.end();
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
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
        texture.dispose();
    }
}
