package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassContext<D extends Data, C extends Context> implements Context, SystemRelated {
    private Class<D> sensedClass;
    private C focusedContext;
    private Signal signal;

    public ClassContext(Class<D> sensedClass) {
        this.sensedClass = sensedClass;
    }

    public Class<D> getSensedClass() {
        return sensedClass;
    }

    public C getFocusedContext() {
        return focusedContext;
    }

    public Signal getSignal() {
        return signal;
    }
}
