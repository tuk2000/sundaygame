package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Condition;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class DataCondition<T extends Data> extends Condition {
    public DataCondition(T t, Signal... signals) {
        super(new HashSet<>(), new HashSet<>());
    }

    public DataCondition(Class<T> clazz, Signal... signals) {
        super(new HashSet<>(), new HashSet<>());
    }

    public DataCondition(Set<Data> dataSet, Set<Predicate<Data>> constrainSet) {
        super(dataSet, constrainSet);
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }
}
