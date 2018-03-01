package com.sunday.engine.driver;

import com.sunday.engine.common.Data;

public abstract class Driver implements Data {
    private DriverType driverType;

    public Driver(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public abstract void reset();
}
