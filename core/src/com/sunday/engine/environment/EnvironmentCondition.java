package com.sunday.engine.environment;

import com.sunday.engine.rule.Condition;

public abstract class EnvironmentCondition<E extends EnvironmentRelatedData, EC extends EnvironmentDataContext<E>> extends Condition<EC> implements EnvironmentRelated {
    protected EnvironmentCondition() {

    }

    @Override
    protected void generateMainInfo() {
        setMainInfo(
                "Source = [" + (getContext().getEnvironmentData() == null ? " n/a" : getContext().getEnvironmentData()) + "]\n" +
                        "SourceClass = [" + getContext().getEnvironmentsDataClazz().getSimpleName() + "]\n" +
                        "Signals = [" + getSignalNames() + "]"
        );
    }
}
