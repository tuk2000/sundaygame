package com.sunday.engine.examples.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sunday.engine.model.property.Movement;
import com.sunday.engine.model.state.Action;
import com.sunday.engine.model.state.Direction;


public class HeroSprite extends Sprite {
    private Movement movement;
    private HeroAnimation heroAnimation;

    public HeroSprite() {
        movement = new Movement();
        heroAnimation = new HeroAnimation();
        movement.action = Action.Running;
    }

    public void jump() {
        movement.action = Action.Jumping;
    }

    public void trunaround() {
        movement.direction = Direction.Left;
    }

    public Texture getAnimationTexture() {
        return heroAnimation.getKeyFrame(movement);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(heroAnimation.getKeyFrame(movement), getX(), getY());
    }
}
