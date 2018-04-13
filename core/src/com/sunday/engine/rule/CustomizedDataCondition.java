package com.sunday.engine.rule;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;

public class CustomizedDataCondition<CD extends CustomizedData, CDC extends CustomizedDataContext<CD>> extends PreAssignedDataCondition<CD, CDC> {

    public CustomizedDataCondition(CD cd, Signal... signals) {
        super(cd, signals);
    }

    public <S extends Signal> CustomizedDataCondition(CD cd, Class<S> signalTypeClass) {
        super(cd, signalTypeClass);
    }

    @Override
    protected void generateExtraInfo(CDC context) {
        setExtraInfoEntry("ConditionType", "CustomizedDataCondition");
    }

    @Override
    public boolean test(CDC cdc) {
        return false;
    }
}
