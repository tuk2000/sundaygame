package com.sunday.engine.model.property;

import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.signal.DataSignal;

import java.util.ArrayList;
import java.util.List;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, OutlookSignal.class}, contextClass = CustomizedDataContext.class)
public class Outlook implements CustomizedData {
    public final Vector2 dimension = new Vector2();
    public final List<ViewLayer> viewLayers = new ArrayList<>();
}
