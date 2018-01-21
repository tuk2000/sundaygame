package com.sunday.game.engine.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

import java.util.ArrayList;
import java.util.List;

public class Outlook implements Data {
    public Shape.Type shape = Shape.Type.Circle;
    public final Vector2 dimension = new Vector2();
    public final List<ViewLayer> viewLayers = new ArrayList<>();
}
