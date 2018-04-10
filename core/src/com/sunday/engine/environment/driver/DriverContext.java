package com.sunday.engine.environment.driver;

import com.sunday.engine.environment.EnvironmentDataContext;

public class DriverContext<D extends Driver> extends EnvironmentDataContext<D> implements DriverRelated {
    public DriverContext(D driver) {
        super(driver);
    }
}
