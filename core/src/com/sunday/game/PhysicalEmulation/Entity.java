package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public class Entity {
    private Vector2 acc;
    private Vector2 v;
    public Entity(){

    }
    public Vector2 getAcceleration(){
        return acc;
    }
    public void setAcceleration(Vector2 acc) {
        this.acc = acc;
    }

    public Vector2 getV() {
        return v;
    }

    public void setV(Vector2 v) {
        this.v = v;
    }


}
