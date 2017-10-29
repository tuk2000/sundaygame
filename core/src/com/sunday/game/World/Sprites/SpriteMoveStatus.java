package com.sunday.game.World.Sprites;

import com.badlogic.gdx.math.Vector2;

public class SpriteMoveStatus {
    public enum FacingDirection {
        Left, Right
    }

    public enum Action {
        Running, StandStill, Jumping, Fighting
    }

    public FacingDirection facingDirection = FacingDirection.Right;
    public Action action = Action.StandStill;
}
