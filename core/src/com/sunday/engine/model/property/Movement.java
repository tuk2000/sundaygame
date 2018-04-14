package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.model.state.Action;
import com.sunday.engine.model.state.Direction;

@DataMark(type = DataType.Customized, signalClass = {DataSignal.class, MovementSignal.class}, contextClass = CustomizedDataContext.class)
public class Movement implements CustomizedData {
    public Direction direction = Direction.Right;
    public Action action = Action.StandStill;
    public float angularVelocity = 0;
    public float angle = 0;
    public Vector2 speed = new Vector2(0, 0);
    public Vector2 position = new Vector2(0, 0);
}
