package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassContext<C extends Context> implements Context, SystemRelated {
    private Class<? extends Data> sensedClass;
    private C focusedContext;
    private Signal signal;

    public ClassContext(Class<? extends Data> sensedClass) {
        this.sensedClass = sensedClass;
    }

    public Class getSensedClass() {
        return sensedClass;
    }

    public C getFocusedContext() {
        return focusedContext;
    }

    public void setFocusedContext(C context) {
        focusedContext = context;
    }

    public Signal getSignal() {
        return signal;
    }

}
