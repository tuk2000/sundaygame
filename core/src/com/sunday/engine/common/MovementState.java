package com.sunday.engine.common;

import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.common.enums.Action;
import com.sunday.engine.common.enums.Direction;

public class MovementState implements Data {
    public Direction direction = Direction.Right;
    public Action action = Action.StandStill;
    public Vector2 speed = new Vector2(0, 0);
    public Vector2 position = new Vector2(0, 0);
}
