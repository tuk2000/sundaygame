package com.sunday.engine.environment;

import com.sunday.engine.rule.DataCondition;

public abstract class EnvironmentCondition<E extends EnvironmentData, EC extends EnvironmentDataContext<E>> extends DataCondition<E, EC> implements EnvironmentRelated {

    protected EnvironmentCondition() {
    }

    @Override
    protected void generateExtraInfo(EC environmentDataContext) {
        setExtraInfoEntry("ConditionType", "EnvironmentCondition");
    }

}
