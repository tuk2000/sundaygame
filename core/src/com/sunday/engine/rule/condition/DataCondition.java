package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Condition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class DataCondition<T extends Data> extends Condition {
    public DataCondition(Map<Data, Predicate<Data>> clusters) {
        super(clusters);
    }

    public static DataCondition dataSignals(Data t, Signal... signals) {
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        //Predicate<Data> dataPredicate=data ->
        //clusters.put();
        return new DataCondition(clusters);
    }

    public static DataCondition classSignals(Class<? extends Data> clazz, Signal... signals) {
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        //Predicate<Data> dataPredicate=data ->
        //clusters.put();
        return new DataCondition(clusters);
    }
}
