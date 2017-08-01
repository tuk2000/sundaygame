package com.sunday.game.Player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class anim extends Animation {
    public anim(float frameDuration, Array keyFrames) {
        super(frameDuration, keyFrames);
    }

    public anim(float frameDuration, Array keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
    }

    public anim(float frameDuration, Object[] keyFrames) {
        super(frameDuration, keyFrames);
    }
}
