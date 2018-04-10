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
        generateMainInfo();
        generateExtraInfo();
    }

    public <S extends Signal> CustomizedDataCondition(CD cd, Class<S> signalTypeClass) {
        customizedData = cd;
        signalCondition.setSignals(signalTypeClass.getEnumConstants());
        generateMainInfo();
        generateExtraInfo();
    }

    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "CustomizedDataCondition");
    }

    @Override
    protected void generateMainInfo() {
        setMainInfoEntry("Source ", customizedData.toString());
        setMainInfoEntry("SourceClass ", customizedData.getClass().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }

    public CD getCustomizedData() {
        return customizedData;
    }

    @Override
    public boolean test(CustomizedDataContext<CD> cdCustomizedDataContext) {
        return false;
    }
}
