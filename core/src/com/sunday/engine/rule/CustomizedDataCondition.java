package com.sunday.engine.rule;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;

public class CustomizedDataCondition<CD extends CustomizedData> extends Condition<CustomizedDataContext<CD>> {
    private CD customizedData;
    protected SignalCondition<CustomizedDataContext<CD>> signalCondition
            = new SignalCondition<>(CustomizedDataContext::getSignal);

    public CustomizedDataCondition(CD cd, Signal... signals) {
        customizedData = cd;
        signalCondition.setSignals(signals);
    }

    public <S extends Signal> CustomizedDataCondition(CD cd, Class<S> signalTypeClass) {
        customizedData = cd;
        signalCondition.setSignals(signalTypeClass.getEnumConstants());
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
