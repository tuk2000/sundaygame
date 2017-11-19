package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sunday.game.engine.common.MovementState;
import com.sunday.game.engine.common.enums.Action;
import com.sunday.game.engine.common.enums.Direction;


public class HeroSprite extends Sprite {
    private MovementState movementState;
    private HeroAnimation heroAnimation;

    public HeroSprite() {
        movementState = new MovementState();
        heroAnimation = new HeroAnimation();
        movementState.action = Action.Running;
    }

    public void jump() {
        movementState.action = Action.Jumping;
    }

    public void trunaround() {
        movementState.direction = Direction.Left;
    }

    public Texture getAnimationTexture() {
        return heroAnimation.getKeyFrame(movementState);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(heroAnimation.getKeyFrame(movementState), getX(), getY());
    }
}
