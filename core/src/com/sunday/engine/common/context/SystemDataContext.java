package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class SystemDataContext<SD extends SystemData> implements Context, SystemRelated {
    private Class<SD> systemDataClass;
    private SD systemData;
    private Signal signal;

    public SystemDataContext(SD systemData) {
        systemDataClass = (Class<SD>) systemData.getClass();
        this.systemData = systemData;
    }

    public SD getSystemData() {
        return systemData;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public Signal getSignal() {
        return signal;
    }

    public Class<SD> getSystemDataClass() {
        return systemDataClass;
    }
}
