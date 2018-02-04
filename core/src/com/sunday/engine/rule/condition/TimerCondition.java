package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.rule.Condition;

import java.util.Set;
import java.util.function.Predicate;

public class TimerCondition extends Condition {
    public TimerCondition(Set<Data> dataSet, Set<Predicate<Data>> constrainSet) {
        super(dataSet, constrainSet);
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }
}
