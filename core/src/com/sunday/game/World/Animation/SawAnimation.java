package com.sunday.game.World.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.sunday.game.GameFramework.GameFramework;

public class SawAnimation {
    private Animation spinningAnimation;

    public SawAnimation() {
        Array<Texture> keyFrames = new Array<>();
        for (int i = 1; i <= 20; i++) {
            keyFrames.add(GameFramework.Resource.getAsset("Enemies/saw" + i + ".png"));
        }
        spinningAnimation = new Animation(AnimationSetting.FramePerSecond,keyFrames, Animation.PlayMode.LOOP);
    }

    public Texture getKeyFrame() {
        return (Texture)spinningAnimation.getKeyFrame(AnimationSetting.DeltaTime);
    }
}
