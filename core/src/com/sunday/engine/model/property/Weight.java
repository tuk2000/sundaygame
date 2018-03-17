package com.sunday.engine.model.property;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Unit;
import com.sunday.engine.common.Vector1D;

public class Weight extends Vector1D implements Data {
    public Weight(float value) {
        super(Unit.Kg);
    }
}
