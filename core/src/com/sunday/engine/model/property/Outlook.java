package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.common.CustomizedData;

import java.util.ArrayList;
import java.util.List;

public class Outlook implements CustomizedData {
    public final Vector2 dimension = new Vector2();
    public final List<ViewLayer> viewLayers = new ArrayList<>();
}
