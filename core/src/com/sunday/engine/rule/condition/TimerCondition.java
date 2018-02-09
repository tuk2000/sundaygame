package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.rule.Condition;

import java.util.Map;
import java.util.function.Predicate;

public class TimerCondition extends Condition {

    public TimerCondition(Map<Data, Predicate<Data>> clusters) {
        super(clusters);
    }
}
