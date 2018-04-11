package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.propertyholder.Customized;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CustomizedDataContext<CD extends CustomizedData> implements Context, Customized {
    private CD customizedData;
    private Signal signal;
    private Map<Predicate<CustomizedDataContext<CD>>, Consumer<CustomizedDataContext<CD>>> map
            = new HashMap<>();


    public CustomizedDataContext(CD customizedData) {
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

    public void setEvaluateConnection(Predicate<CustomizedDataContext<CD>> contextPredicate, Consumer<CustomizedDataContext<CD>> contextConsumer) {
        map.put(contextPredicate, contextConsumer);
    }
}