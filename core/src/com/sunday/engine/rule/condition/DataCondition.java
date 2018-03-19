package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Tracer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DataCondition<T extends Data> extends Condition<T> {

    protected List<Predicate<T>> predicates = new ArrayList<>();
    private boolean isAndOperation = true;

    public DataCondition(T t, Signal... signals) {
        setData(t);
        setSignals(signals);
    }

    protected DataCondition() {
    }

    protected void setAndOperation(boolean isAndOperation) {
        this.isAndOperation = isAndOperation;
    }

    protected void addPredicate(Predicate<T> predicate) {
        predicates.add(predicate);
    }

    @Override
    protected boolean isSatisfied() {
        List<Boolean> result = new ArrayList<>();
        predicates.forEach(predicate -> result.add(predicate.test(getData())));
        if (isAndOperation) {
            result.add(true);
            return result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean & aBoolean2)).get();
        } else {
            result.add(false);
            return result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
        }
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        if (getData() == null) return;
        getTracers().clear();
        getSignals().forEach(signal -> {
            Tracer tracer = new Tracer(this, getData(), signal);
            getTracers().add(tracer);
        });
        getTracers().forEach(tracer -> {
            systemPort.addConnection(getData(), tracer);
        });
        generateMainInfo();
    }
}
