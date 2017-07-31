package com.sunday.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

public class Player implements ApplicationListener {
    SpriteBatch batch;
    Texture player;
    Texture background;
    TextureRegion[][] tRegions;
    Sprite sprite;
    Sprite bgSprite;
    int frame=0,zeile = 0;
    OrthographicCamera cam;
    private float rotationSpeed;
    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        player = new Texture("playerSp.png");
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        tRegions = TextureRegion.split(player,275,385);
        sprite = new Sprite(tRegions[0][0]);
        //Camera
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
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
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //cam.update();
        //batch.setProjectionMatrix(cam.combined);
        boden();
        Background();
        handleInput();
        batch.begin();
        sprite.setSize(75,75);
        sprite.draw(batch);
        batch.end();
    }
    public void boden(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ROYAL);
        shapeRenderer.circle(520,0,250);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(520,0,150);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(520,0,50);
        shapeRenderer.end();

    }
    public void Background(){
        background = new Texture("bg/bg.png");
        bgSprite = new Sprite(background);
        batch.begin();
        bgSprite.draw(batch);
        bgSprite.setPosition(600,0);
        bgSprite.setRotation(180.0f);
        bgSprite.draw(batch);
        batch.end();
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if (sprite.getX()> -200) {
                sprite.translateX(-3f);
                sprite.translateY(10f);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if (sprite.getX()<1030) {
                sprite.translateX(11f);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();
    }
    @Override
    public void dispose() {

    }
}


