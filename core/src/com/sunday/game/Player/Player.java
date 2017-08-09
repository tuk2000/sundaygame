package com.sunday.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;


public class Player extends Sprite implements InputProcessor {

    private float speed = 60 * 2, gravity = 60 * 1.8f, animationTime = 0, increment;
    private Vector2 velocity = new Vector2();

    private boolean jumpState;
    //rest : player will do nothing
    private Animation left,right,rest;
    private TiledMapTileLayer collisionLayer;

    private String intercept = "intercept";

    public Player(Animation rest,Animation left,Animation right,TiledMapTileLayer collisionLayer){
        super((Texture) rest.getKeyFrame(0));
        this.rest = rest;
        this.left = left;
        this.right = right;
        this.collisionLayer = collisionLayer;
        setSize(collisionLayer.getWidth()/3,collisionLayer.getHeight()*1.25f);
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }

    public void update(float delta){
        //here we apply gravity
        velocity.y -= gravity*delta;

        //velocity clamp
        if (velocity.y > speed){
            velocity.y = speed;
        }else if (velocity.y < speed){
            velocity.y = -speed;
        }
        //save data
        float oldX = getX(),oldY = getY();
        boolean collisionX = false,collisionY = false;

        //player movement on x
        setX(getX() + velocity.x *delta);
        //player movement on y
        setX(getY() + velocity.y *delta);
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
