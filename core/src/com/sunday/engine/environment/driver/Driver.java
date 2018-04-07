package com.sunday.engine.environment.driver;

import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.environment.EnvironmentRelatedData;

public abstract class Driver implements EnvironmentRelatedData, Resettable, DriverRelated {
    private DriverType driverType;

    public Driver(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
