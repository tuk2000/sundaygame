package com.sunday.game.World.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sunday.game.World.Animation.HeroAnimation;


public class HeroSprite extends Sprite {
    private SpriteMoveStatus spriteMoveStatus;
    private HeroAnimation heroAnimation;

    public HeroSprite() {
        spriteMoveStatus = new SpriteMoveStatus();
        heroAnimation = new HeroAnimation();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(heroAnimation.getKeyFrame(spriteMoveStatus), getX(), getY());
    }
}
