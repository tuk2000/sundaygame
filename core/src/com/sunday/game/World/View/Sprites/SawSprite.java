package com.sunday.game.World.View.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sunday.game.World.View.Animation.SawAnimation;

public class SawSprite extends Sprite {
    private SawAnimation sawAnimation;

    public SawSprite() {
        sawAnimation = new SawAnimation();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(sawAnimation.getKeyFrame(), getX(), getY());
    }

}
