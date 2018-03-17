package com.sunday.engine.common;

import com.badlogic.gdx.math.Vector2;

public class Vector2D extends Vector2 {
    final Unit unit;

    public Vector2D(Unit unit) {
        this.unit = unit;
    }

    public Vector2D(float x, float y, Unit unit) {
        set(x, y);
        this.unit = unit;
    }

    public Vector2D(Vector2 v, Unit unit) {
        set(v);
        this.unit = unit;
    }
}
