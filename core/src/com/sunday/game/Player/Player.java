package com.sunday.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;


public class Player extends Sprite {

    private float speed = 60 * 2, gravity = 0,momentum = 0, animationTime = 0, increment;
    private Vector2 velocity = new Vector2();

    private boolean jumpState;
    //rest : player will do nothing
    private Animation left, right, rest;
    private TiledMapTileLayer collisionLayer;

    private String intercept = "intercept";
    private Vector2 gravityVector = new Vector2(0,-1);

    //public Player(Animation rest,Animation left,Animation right,TiledMapTileLayer collisionLayer){
    public Player(Texture texture, float x, float y) {
        this.setTexture(texture);
        this.setPosition(x, 256);
       /* this.rest = rest;
        this.left = left;
        this.right = right;
        this.collisionLayer = collisionLayer;
        setSize(collisionLayer.getWidth()/3,collisionLayer.getHeight()*1.25f);*/
    }

    @Override
    public void draw(Batch batch) {
        update();
        batch.draw(this.getTexture(), this.getX(), this.getY(), 64, 64);
    }

    public void update() {
        float frame = Gdx.graphics.getFramesPerSecond();
        frame = (frame == 0)? 60:frame;
        //gravity of the world
        gravity = 2.81f/frame;
        if (this.getY()<=30){
            momentum = 0;
        }else {
            momentum = Math.min(gravity * frame, momentum + gravity);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            momentum = gravity *5;
        }

        this.translateX(gravityVector.x * momentum);
        this.translateY(gravityVector.y * momentum);
    }

}
