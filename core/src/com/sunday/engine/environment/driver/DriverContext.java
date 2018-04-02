package com.sunday.engine.environment.driver;

import com.sunday.engine.common.MetaDataContext;

public class DriverContext<D extends Driver> extends MetaDataContext<D> {
    public DriverContext(D driver) {
        super(driver);
    }
}
