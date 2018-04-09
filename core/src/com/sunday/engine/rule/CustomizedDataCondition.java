package com.sunday.engine.rule;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;

public class CustomizedDataCondition<CD extends CustomizedData> extends Condition<CustomizedDataContext<CD>> {
    private CD customizedData;

    public CustomizedDataCondition(CD cd, Signal... signals) {
        customizedData = cd;
        setSignals(signals);
    }

    public <S extends Signal> CustomizedDataCondition(CD cd, Class<S> signalTypeClass) {
        customizedData = cd;
        setSignals(signalTypeClass.getEnumConstants());
    }

    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "Customized");
    }

    @Override
    protected void generateMainInfo() {
        CD data = getContext().getCustomizedData();
        setMainInfoEntry("Source ", data.toString());
        setMainInfoEntry("SourceClass ", data.getClass().getSimpleName());
        setMainInfoEntry("Signals", getSignalNames());
    }

    @Override
    public void check() {
        if (isSatisfied()) {
            reaction.accept(getContext());
        }
    }

    public CD getCustomizedData() {
        return customizedData;
    }

}
