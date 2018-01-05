package com.sunday.game.engine.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

public class Outlook implements Data {
    public Shape.Type shape = Shape.Type.Circle;
    public Vector2 dimension = new Vector2();
}
