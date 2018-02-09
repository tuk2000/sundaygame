package com.sunday.engine.event.driver;


import com.sunday.engine.driver.Driver;
import com.sunday.engine.driver.DriverType;
import com.sunday.engine.event.Event;

public class DriverEvent extends Event {

    private Driver driver;

    public DriverEvent(Driver driver) {
        super(driver);
        this.driver = driver;
    }

    public DriverType getDriverType() {
        return driver.getDriverType();
    }
}
