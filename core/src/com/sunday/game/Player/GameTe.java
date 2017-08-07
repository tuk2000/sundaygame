package com.sunday.game.Player;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameTe implements ApplicationListener{
     SpriteBatch batch;
     Texture player1;
     TextureRegion[] animationFrames;
     BitmapFont font;
     Sprite sprite;
     ShapeRenderer shapeRenderer;
     TextureRegion[][] tRegions;
     int frame = 0;
     int zeile = 0;

    @Override
    public void create() {
        player1 = new Texture("playerSp.png");
        batch = new SpriteBatch();
        Player pl = new Player();
        font = new BitmapFont();
        font.setColor(Color.RED);
        shapeRenderer = new ShapeRenderer();
        tRegions = TextureRegion.split(player1,18,385);
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
        },0,1/20f);
        //animation = new Animation(1f/2f,test,36.112,385);
        //sprite.setSize(400,100);
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
        shapeRenderer.rect(70,20,20,20);
        shapeRenderer.rect(0,0,800,20,Color.BLACK,Color.GRAY,Color.DARK_GRAY,Color.LIGHT_GRAY);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch,"Sunday Game",200,200);
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
        //font.dispose();
        //shapeRenderer.dispose();
        player1.dispose();
    }
}
