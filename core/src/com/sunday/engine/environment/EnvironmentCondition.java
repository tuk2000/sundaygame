package com.sunday.engine.environment;

import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.SignalCondition;

public abstract class EnvironmentCondition<E extends EnvironmentData, EC extends EnvironmentDataContext<E>> extends Condition<EC> implements EnvironmentRelated {
    protected SignalCondition<EC> signalCondition = new SignalCondition<>(ec -> ec.getSignal());

    protected EnvironmentCondition() {

    }

    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "EnvironmentCondition");
    }

    @Override
    protected void generateMainInfo() {
        E e = getContext().getEnvironmentData();
        setMainInfoEntry("Source ", (e == null ? " n/a" : e.toString()));
        setMainInfoEntry("SourceClass", getContext().getEnvironmentsDataClazz().getSimpleName());
        setMainInfoEntry("Signals", signalCondition.getSignalNames());
    }

    public void setEnvironmentContext(EC environmentContext) {
        setContext(environmentContext);
        generateMainInfo();
        generateExtraInfo();
    }
}
