package com.sunday.engine.environment;

import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.SignalCondition;

public abstract class EnvironmentCondition<E extends EnvironmentData, EC extends EnvironmentDataContext<E>> extends Condition<EC> implements EnvironmentRelated {
    protected SignalCondition<EC> signalCondition = new SignalCondition<>(ec -> ec.getSignal());

    protected EnvironmentCondition() {

    }

    @Override
    protected void generateExtraInfo(EC environmentDataContext) {
        setExtraInfoEntry("ConditionType", "EnvironmentCondition");
    }

    @Override
    protected void generateMainInfo(EC environmentDataContext) {
        setMainInfoEntry("Source ", environmentDataContext.toString());
        setMainInfoEntry("SourceClass", environmentDataContext.getDataClass().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }

}
