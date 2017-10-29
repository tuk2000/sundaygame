package com.sunday.game.World.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sunday.game.World.Animation.SawAnimation;

public class SawSprite extends Sprite {
    private SawAnimation sawAnimation;

    public SawSprite() {
        sawAnimation = new SawAnimation();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sawAnimation.getKeyFrame(),getX(), getY());
    }

}
