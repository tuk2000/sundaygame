package com.sunday.engine.event.driver;


import com.sunday.engine.driver.DriverData;
import com.sunday.engine.driver.DriverType;
import com.sunday.engine.event.Event;

public class DriverEvent extends Event {

    private DriverData driverData;

    public DriverEvent(DriverData driverData) {
        super(driverData);
        this.driverData = driverData;
    }

    public DriverType getDriverType() {
        return driverData.getDriverType();
    }
}
