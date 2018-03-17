package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.common.Data;

import java.util.ArrayList;
import java.util.List;

public class Outlook implements Data {
    public final Vector2 dimension = new Vector2();
    public final List<ViewLayer> viewLayers = new ArrayList<>();
    public Shape.Type shape = Shape.Type.Circle;
}
