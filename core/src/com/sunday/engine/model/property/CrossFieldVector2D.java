package com.sunday.engine.model.property;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Vector2D;

public interface CrossFieldVector2D extends Data {
    Vector2D getWindow();

    Vector2D getViewPort();

    Vector2D getPhysic();

    Vector2D getGL();
}
