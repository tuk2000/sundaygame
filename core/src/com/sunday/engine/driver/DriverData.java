package com.sunday.engine.driver;

import com.sunday.engine.common.Data;

public abstract class DriverData implements Data {
    private DriverType driverType;

    public DriverData(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
