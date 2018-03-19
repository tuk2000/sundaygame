package com.sunday.engine.common;

public class Vector1D {
    public final Unit unit;
    public float value = 0.0f;

    public Vector1D(Unit unit) {
        this.unit = unit;
    }

    public Vector1D(float value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }
}
