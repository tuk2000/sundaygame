package com.sunday.engine.environment.event.driver;


import com.sunday.engine.common.Signal;
import com.sunday.engine.environment.driver.Driver;
import com.sunday.engine.environment.event.Event;

public class DriverEvent<T extends Driver> extends Event {
    public DriverEvent(T driver, Signal signal) {
        super(driver, signal);
    }
}
