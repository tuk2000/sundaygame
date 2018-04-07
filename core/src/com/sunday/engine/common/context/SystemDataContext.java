package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.data.SystemRelatedData;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class SystemDataContext<SD extends SystemRelatedData> implements Context, SystemRelated {
    private Class<SD> systemRelatedDataClass;
    private SD systemRelatedData;
    private Signal signal;

    public SystemDataContext(SD systemRelatedData) {
        systemRelatedDataClass = (Class<SD>) systemRelatedData.getClass();
        this.systemRelatedData = systemRelatedData;
    }

    public SD getSystemRelatedData() {
        return systemRelatedData;
    }

    public Signal getSignal() {
        return signal;
    }
}
