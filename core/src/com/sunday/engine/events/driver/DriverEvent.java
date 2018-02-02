package com.sunday.engine.events.driver;


import com.sunday.engine.common.enums.Driver;
import com.sunday.engine.events.Event;

public class DriverEvent extends Event {

    private Driver driver;

    public DriverEvent(Driver driver) {
        super(driver);
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
}
