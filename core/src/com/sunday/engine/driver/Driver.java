package com.sunday.engine.driver;

import com.sunday.engine.common.MetaData;

public abstract class Driver implements MetaData {
    private DriverType driverType;

    public Driver(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
