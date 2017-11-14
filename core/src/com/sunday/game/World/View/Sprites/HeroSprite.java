package com.sunday.game.World.View.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sunday.game.World.View.Animation.HeroAnimation;


public class HeroSprite extends Sprite {
    private SpriteMoveStatus spriteMoveStatus;
    private HeroAnimation heroAnimation;

    public HeroSprite() {
        spriteMoveStatus = new SpriteMoveStatus();
        heroAnimation = new HeroAnimation();
        spriteMoveStatus.action = SpriteMoveStatus.Action.Running;
    }

    public void jump() {
        spriteMoveStatus.action = SpriteMoveStatus.Action.Jumping;
    }

    public void trunaround() {
        spriteMoveStatus.facingDirection = SpriteMoveStatus.FacingDirection.Left;
    }

    public Texture getAnimationTexture() {
        return heroAnimation.getKeyFrame(spriteMoveStatus);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(heroAnimation.getKeyFrame(spriteMoveStatus), getX(), getY());
    }
}
