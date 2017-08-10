package com.sunday.game.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

public class Saw extends Sprite {
    private float x,y;
    private TextureRegion tr1,tr2,tr3,tr4,tr5,tr6,tr7,tr8;
    private Sprite sprite;
    private int frame = 0;
    private int zeile = 0;

    public Saw(Texture saw,float x,float y){
        this.x = x;
        this.y = y;
        this.setTexture(saw);
        this.setPosition(x,y);
    }
    @Override
    public void draw(Batch batch){
        tr1 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr2 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr3 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr4 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr5 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr6 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr7 = new TextureRegion(new Texture("Enemies/saw.png"));
        tr8 = new TextureRegion(new Texture("Enemies/saw.png"));
        Animation playerAnimation = new Animation(0.1f, tr1, tr2, tr3, tr4);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);
        batch.draw(tr1,64,64);
    }


}
