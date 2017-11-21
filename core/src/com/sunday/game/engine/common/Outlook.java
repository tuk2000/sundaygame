package com.sunday.game.engine.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

public class Outlook {
    public Shape.Type shape;
    public Vector2 dimension;
    public boolean sizeChanged;

    public Outlook(Shape.Type shape, Vector2 dimension) {
        this.shape = shape;
        this.dimension = dimension;
        sizeChanged = false;
    }

}
