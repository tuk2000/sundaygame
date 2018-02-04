package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

import java.util.Set;
import java.util.function.Predicate;

public abstract class Condition implements Data {
    protected Set<Data> dataSet;
    protected Set<Predicate<Data>> constrainSet;

    public Condition(Set<Data> dataSet, Set<Predicate<Data>> constrainSet) {
        this.dataSet = dataSet;
        this.constrainSet = constrainSet;
    }

    protected abstract boolean isSatisfied();
}
