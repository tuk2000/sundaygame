package com.sunday.engine.environment;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

public class EnvironmentDataContext<ED extends EnvironmentData> implements Context, EnvironmentRelated {
    private Class<ED> environmentsDataClazz;
    private ED environmentData;
    private Signal signal;

    public EnvironmentDataContext(ED environmentRelatedData) {
        environmentsDataClazz = (Class<ED>) environmentRelatedData.getClass();
        this.environmentData = environmentRelatedData;
    }

    public ED getEnvironmentData() {
        return environmentData;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public Signal getSignal() {
        return signal;
    }

    public Class<ED> getEnvironmentsDataClazz() {
        return environmentsDataClazz;
    }

    public void evaluate() {
        System.out.println("Evaluating : "+getEnvironmentsDataClazz()+" - "+getEnvironmentData()+" "+getSignal());
    }
}
