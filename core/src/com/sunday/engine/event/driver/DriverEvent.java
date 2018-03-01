package com.sunday.engine.event.driver;


import com.sunday.engine.driver.Driver;
import com.sunday.engine.event.Event;

public class DriverEvent<T extends Driver> extends Event {
    public DriverEvent(T driver) {
        super(driver);
    }
}
