package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DataCondition<T extends Data, S extends Signal> extends Condition<T, S> {

    protected List<Predicate<T>> predicates = new ArrayList<>();
    private boolean isAndOperation = true;
    private List<Boolean> result = new ArrayList<>();

    public DataCondition(T t, S... signals) {
        setData(t);
        setSignals(signals);
    }

    public DataCondition(T t, Class<S> signalTypeClass) {
        setData(t);
        setSignals(signalTypeClass.getEnumConstants());
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
    public void connectWith(SystemPort systemPort) {
        systemPort.addConnection(getData(), this);
        generateMainInfo();
    }

    @Override
    public void notify(Data data, Signal signal) {
        result.clear();
        predicates.forEach(predicate -> result.add(predicate.test(getData())));
        boolean isSatisfied;
        if (isAndOperation) {
            result.add(true);
            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean & aBoolean2)).get();
        } else {
            result.add(false);
            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
        }
        isSatisfied = isSatisfied & getSignals().contains(signal);
        if (isSatisfied) {
            getReaction().accept(data, signal);
        }
    }
}
