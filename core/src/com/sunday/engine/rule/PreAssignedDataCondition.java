package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.DataContext;

public abstract class PreAssignedDataCondition<D extends Data, DC extends DataContext<D>> extends DataCondition<D, DC> {

    protected PreAssignedDataCondition(D data, Signal... signals) {
        this.data = data;
        signalCondition.setSignals(signals);
    }

    protected <S extends Signal> PreAssignedDataCondition(D data, Class<S> signalTypeClass) {
        this(data, signalTypeClass.getEnumConstants());
    }
}
