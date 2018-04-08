package com.sunday.engine.environment;

import com.sunday.engine.rule.Condition;

public abstract class EnvironmentCondition<E extends EnvironmentData, EC extends EnvironmentDataContext<E>> extends Condition<EC> implements EnvironmentRelated {
    protected EnvironmentCondition() {

    }


    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "EnvironmentRelated");
    }

    @Override
    protected void generateMainInfo() {
        E e = getContext().getEnvironmentData();
        setMainInfoEntry("Source ", (e == null ? " n/a" : e.toString()));
        setMainInfoEntry("SourceClass", getContext().getEnvironmentsDataClazz().getSimpleName());
        setMainInfoEntry("Signals", getSignalNames());
    }

    public void setEnvironmentContext(EC environmentContext) {
        setContext(environmentContext);
    }
}
