package com.sunday.engine.model.property;

import com.sunday.engine.common.Unit;
import com.sunday.engine.common.Vector2D;

public class Size implements CrossFieldVector2D {
    public Vector2D window = new Vector2D(Unit.Pixel);
    public Vector2D viewport = new Vector2D(Unit.V_Pixel);
    public Vector2D physic = new Vector2D(Unit.Meter);
    public Vector2D gl = new Vector2D(Unit.GL_One);

    @Override
    public Vector2D getWindow() {
        return window;
    }

    @Override
    public Vector2D getViewPort() {
        return viewport;
    }

    @Override
    public Vector2D getPhysic() {
        return physic;
    }

    @Override
    public Vector2D getGL() {
        return gl;
    }
}
