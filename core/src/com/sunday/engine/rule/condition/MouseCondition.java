package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.rule.Condition;

import java.util.Set;
import java.util.function.Predicate;

public class MouseCondition extends Condition {
    public MouseCondition(Set<Data> dataSet, Set<Predicate<Data>> constrainSet) {
        super(dataSet, constrainSet);
    }

    @Override
    protected boolean isSatisfied() {
        return false;
    }
}
