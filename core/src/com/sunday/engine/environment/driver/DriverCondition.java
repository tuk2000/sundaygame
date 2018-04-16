package com.sunday.engine.environment.driver;

import com.sunday.engine.environment.EnvironmentCondition;

import java.util.function.Predicate;

public abstract class DriverCondition<D extends Driver> extends EnvironmentCondition<D, DriverContext<D>> implements DriverRelated {
    protected Predicate<DriverContext<D>> driverPredicate;

    protected DriverCondition(Predicate<DriverContext<D>> driverPredicate) {
        this.driverPredicate = driverPredicate;
    }

    public boolean test(DriverContext<D> driverContext) {
        return signalCondition.and(driverPredicate).test(driverContext);
    }
}
