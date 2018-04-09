package com.sunday.engine.environment;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EnvironmentDataContext<ED extends EnvironmentData> implements Context, EnvironmentRelated {
    private Class<ED> environmentsDataClazz;
    private ED environmentData;
    private Signal signal;
    private Map<Predicate<EnvironmentDataContext<ED>>, Consumer<EnvironmentDataContext<ED>>> map = new HashMap<>();

    public EnvironmentDataContext(ED environmentRelatedData) {
        environmentsDataClazz = (Class<ED>) environmentRelatedData.getClass();
        this.environmentData = environmentRelatedData;
    }

    public ED getEnvironmentData() {
        return environmentData;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public void setEvaluateConnection(Predicate<EnvironmentDataContext<ED>> contextPredicate, Consumer<EnvironmentDataContext<ED>> contextConsumer) {
        map.put(contextPredicate, contextConsumer);
    }

    public Class<ED> getEnvironmentsDataClazz() {
        return environmentsDataClazz;
    }

    public void evaluate() {
        System.out.println("Evaluating : " + getEnvironmentsDataClazz() + " - " + getEnvironmentData() + " " + getSignal());
        map.forEach((contextPredicate, contextConsumer) -> {
            if (contextPredicate.test(this)) {
                contextConsumer.accept(this);
            }
        });
    }
}
