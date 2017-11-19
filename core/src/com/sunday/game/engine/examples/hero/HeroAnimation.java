package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.sunday.game.engine.common.AnimationSetting;
import com.sunday.game.engine.common.RoleMovementStatus;
import com.sunday.game.engine.common.enums.Direction;
import com.sunday.game.framework.GameFramework;

public class HeroAnimation {
    private Animation runningAnimation;
    private Animation fightingAnimation;
    private Animation standstillAnimation;
    private Animation jumpingAnimation;

    public HeroAnimation() {
        float fps = AnimationSetting.FramePerSecond;
        Array<Texture> keyFrames = new Array<>();
        for (int i = 1; i <= 10; i++) {
            keyFrames.add(GameFramework.Resource.getAsset("hero/Hero" + i + ".png"));
        }
        runningAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP);
        fightingAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP_RANDOM);
        standstillAnimation = new Animation(fps, keyFrames, Animation.PlayMode.NORMAL);
        jumpingAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP_PINGPONG);
    }

    public Texture getKeyFrame(RoleMovementStatus roleMovementStatus) {
        Texture texture = null;
        Animation currentAnimation = null;
        switch (roleMovementStatus.action) {
            case Running:
                currentAnimation = runningAnimation;
                break;
            case Jumping:
                currentAnimation = jumpingAnimation;
                break;
            case Fighting:
                currentAnimation = fightingAnimation;
                break;
            case StandStill:
            default:
                currentAnimation = standstillAnimation;
        }
        if (currentAnimation != null) {
            texture = (Texture) currentAnimation.getKeyFrame(AnimationSetting.DeltaTime);
            if (roleMovementStatus.direction == Direction.Left) {
                //
            }
        }
        return texture;
    }
}