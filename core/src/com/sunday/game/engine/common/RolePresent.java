package com.sunday.game.engine.common;

import com.badlogic.gdx.math.Vector2;

public class RolePresent {
    public RoleShape roleShape;
    public Vector2 dimension;

    public RolePresent(RoleShape roleShape, Vector2 dimension) {
        this.roleShape = roleShape;
        this.dimension = dimension;
    }
}
