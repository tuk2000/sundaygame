package com.sunday.engine.rule;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;

public class CustomizedDataCondition<CD extends CustomizedData> extends Condition<CustomizedDataContext<CD>> {
    protected SignalCondition<CustomizedDataContext<CD>> signalCondition
            = new SignalCondition<>(CustomizedDataContext::getSignal);
    private CD customizedData;

    public CustomizedDataCondition(CD cd, Signal... signals) {
        customizedData = cd;
        signalCondition.setSignals(signals);
    }

    public <S extends Signal> CustomizedDataCondition(CD cd, Class<S> signalTypeClass) {
        customizedData = cd;
        signalCondition.setSignals(signalTypeClass.getEnumConstants());
    }

    public CD getCustomizedData() {
        return customizedData;
    }

    @Override
    protected void generateExtraInfo(CustomizedDataContext<CD> context) {
        setExtraInfoEntry("ConditionType", "CustomizedDataCondition");
    }

    @Override
    protected void generateMainInfo(CustomizedDataContext<CD> context) {
        setMainInfoEntry("Source ", customizedData.toString());
        setMainInfoEntry("SourceClass ", customizedData.getClass().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }

    @Override
    public boolean test(CustomizedDataContext<CD> cdCustomizedDataContext) {
        return false;
    }

}
