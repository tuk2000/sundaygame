package com.sunday.game.engine.view.sprites;

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
