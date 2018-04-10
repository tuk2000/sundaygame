package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.propertyholder.Customized;

public class CustomizedDataContext<CD extends CustomizedData> implements Context, Customized {
    private Class<CD> customizedDataClass;
    private CD customizedData;
    private Signal signal;

    public CustomizedDataContext(CD customizedData) {
        customizedDataClass = (Class<CD>) customizedData.getClass();
        this.customizedData = customizedData;
    }

    public CD getCustomizedData() {
        return customizedData;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }
}