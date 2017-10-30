package com.sunday.game.World.View.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.game.World.View.Sprites.SpriteMoveStatus;

public class HeroAnimation {
    private Animation runningAnimation;
    private Animation fightingAnimation;
    private Animation standstillAnimation;
    private Animation jumpingAnimation;

    public HeroAnimation() {
        float fps = AnimationSetting.FramePerSecond;
        Array<Texture> keyFrames = new Array<>();
        for (int i = 1; i <= 10; i++) {
            keyFrames.add(GameFramework.Resource.getAsset("Hero/Hero" + i + ".png"));
        }
        runningAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP);
        fightingAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP_RANDOM);
        standstillAnimation = new Animation(fps, keyFrames, Animation.PlayMode.LOOP_REVERSED);
        jumpingAnimation = new Animation(fps, keyFrames, Animation.PlayMode.NORMAL);
    }

    public Texture getKeyFrame(SpriteMoveStatus spriteMoveStatus) {
        Texture texture = null;
        Animation currentAnimation = null;
        switch (spriteMoveStatus.action) {
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
            if (spriteMoveStatus.facingDirection == SpriteMoveStatus.FacingDirection.Left) {
                //
            }
        }
        return texture;
    }
}
