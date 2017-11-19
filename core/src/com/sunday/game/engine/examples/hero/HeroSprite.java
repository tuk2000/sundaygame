package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sunday.game.engine.common.RoleMovementStatus;
import com.sunday.game.engine.common.enums.Action;
import com.sunday.game.engine.common.enums.Direction;


public class HeroSprite extends Sprite {
    private RoleMovementStatus roleMovementStatus;
    private HeroAnimation heroAnimation;

    public HeroSprite() {
        roleMovementStatus = new RoleMovementStatus();
        heroAnimation = new HeroAnimation();
        roleMovementStatus.action = Action.Running;
    }

    public void jump() {
        roleMovementStatus.action = Action.Jumping;
    }

    public void trunaround() {
        roleMovementStatus.direction = Direction.Left;
    }

    public Texture getAnimationTexture() {
        return heroAnimation.getKeyFrame(roleMovementStatus);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(heroAnimation.getKeyFrame(roleMovementStatus), getX(), getY());
    }
}
