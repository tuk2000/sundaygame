package com.sunday.engine.model.property;

import com.sunday.engine.common.SpecificSignal;

public enum OutlookSignal implements SpecificSignal {
    None, Updated, ShapeChanged, DimensionChanged, LayerAdded, LayerDeleted
}
