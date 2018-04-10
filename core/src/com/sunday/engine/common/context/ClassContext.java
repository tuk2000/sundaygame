package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassContext<RC extends Context> implements Context, SystemRelated {
    private Class<? extends Data> sensedClass;
    private RC focusedContext;

    public ClassContext(Class<? extends Data> sensedClass) {
        this.sensedClass = sensedClass;
    }

    public Class getSensedClass() {
        return sensedClass;
    }

    public RC getFocusedContext() {
        return focusedContext;
    }

    public void setFocusedContext(RC context) {
        focusedContext = context;
    }

}
