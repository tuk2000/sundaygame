package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;
import com.sunday.engine.common.data.SystemData;

public class SystemDataCondition<SD extends SystemData, SDC extends SystemDataContext<SD>> extends DataCondition<SD, SDC> {

    @Override
    protected void generateExtraInfo(SDC context) {
        setExtraInfoEntry("ConditionType", "SystemDataCondition");
    }

    @Override
    public boolean test(SDC sdc) {
        return false;
    }
}
