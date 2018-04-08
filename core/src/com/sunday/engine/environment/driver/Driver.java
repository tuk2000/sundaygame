package com.sunday.engine.environment.driver;

import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.environment.EnvironmentData;

public abstract class Driver implements EnvironmentData, Resettable, DriverRelated {
    private DriverType driverType;

    public Driver(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
