package com.sunday.engine.environment.driver;

import com.sunday.engine.environment.EnvironmentCondition;

import java.util.function.Predicate;

public abstract class DriverCondition<D extends Driver> extends EnvironmentCondition<D, DriverContext<D>> implements DriverRelated {

    protected DriverCondition(Predicate<DriverContext<D>> driverContextPredicate) {
        setPredicate(driverContextPredicate);
    }

    @Override
    protected void generateExtraInfo(DriverContext<D> context) {
        super.generateExtraInfo(context);
        setExtraInfoEntry("ConditionType", "DriverCondition");
    }
}
