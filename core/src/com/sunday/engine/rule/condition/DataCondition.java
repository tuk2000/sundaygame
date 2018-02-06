package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.ClassSensor;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Tracer;
import com.sunday.engine.rule.tracer.SignalTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DataCondition<T extends Data> extends Condition {
    public DataCondition(Map<Data, Predicate<Data>> clusters) {
        super(clusters);
    }

    @Override
    protected boolean isSatisfied() {
        List<Boolean> result = new ArrayList<>();
        result.add(true);
        tracers.forEach((data, tracer) -> result.add(((SignalTracer) tracer).isTracedSignal()));
        return result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
    }

    public static DataCondition dataSignals(Data t, Signal... signals) {
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        DataCondition dataCondition = new DataCondition(clusters);
        String names="";
        for (Signal signal : signals) {
            names=names+signal.name()+" ";
            clusters.put(t, null);
            Tracer tracer = new SignalTracer(dataCondition, t, signal);
            dataCondition.tracers.put(t, tracer);
        }
        dataCondition.setInfo("From Instance of Class [" + t.getClass().getSimpleName() + "] excepting signals :" + names);
        return new DataCondition(clusters);
    }

    public static DataCondition classSignals(Class<? extends Data> clazz, Signal... signals) {
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        DataCondition dataCondition = new DataCondition(clusters);
        String names="";
        ClassSensor classSensor = ClassSensor.getClassSensor(clazz);
        for (Signal signal : signals) {
            names=names+signal.name()+" ";
            clusters.put(classSensor, null);
            dataCondition.tracers.put(classSensor, new SignalTracer(dataCondition, classSensor, signal));
        }
        dataCondition.setInfo(" From Class [" + clazz.getSimpleName() + "] excepting signals :" + names);
        return dataCondition;
    }
}
