package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataContext;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;

public class DataCondition<D extends Data> extends Condition<DataContext<D>> {


    public DataCondition(D d, Signal... signals) {
        context = ContextBuilder.buildDataContext(d);
        setSignals(signals);
    }

    public DataCondition(D d, Class<Signal> signalTypeClass) {
        context = ContextBuilder.buildDataContext(d);
        setSignals(signalTypeClass.getEnumConstants());
    }

    protected DataCondition() {

    }

    @Override
    protected void generateMainInfo() {
        D data = context.getData();
        setMainInfo(
                "Source = [" + data + "]\n" +
                        "SourceClass = [" + data.getClass().getSimpleName() + "]\n" +
                        "Signals = [" + getSignalNames() + "]"
        );
    }


    @Override
    public void connectWith(SystemPort systemPort) {
//        getSignals().forEach(tracer -> {
//            systemPort.removeConnection(context.getData(), this);
//        });
//        systemPort.addConnection(context.getData(), this);
//        generateMainInfo();
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

//    @Override
//    public void notify(Data data, Signal signal) {
//        result.clear();
//        predicates.forEach(predicate -> result.add(predicate.test(context)));
//        boolean isSatisfied;
//        if (isAndOperation) {
//            result.add(true);
//            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean & aBoolean2)).get();
//        } else {
//            result.add(false);
//            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
//        }
//        isSatisfied = isSatisfied & getSignals().contains(signal);
//        if (isSatisfied) {
//            reaction.accept(context);
//        }
//    }
}
