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

    public EnvironmentDataContext(Class<ED> clazz) {
        environmentsDataClazz = clazz;
    }

    public ED getEnvironmentData() {
        return environmentData;
    }

    public Signal getSignal() {
        return signal;
    }


    public Class<ED> getEnvironmentsDataClazz() {
        return environmentsDataClazz;
    }
}
