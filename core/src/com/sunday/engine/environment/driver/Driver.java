package com.sunday.engine.environment.driver;

import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.environment.EnvironmentData;

@DataMark(type = DataType.System, signalClass = {DataSignal.class}, contextClass = DriverContext.class)
public abstract class Driver implements EnvironmentData, Resettable, DriverRelated {
    private DriverType driverType;

    public Driver(DriverType driverType) {
        this.driverType = driverType;
    }

    public DriverType getDriverType() {
        return driverType;
    }
}
