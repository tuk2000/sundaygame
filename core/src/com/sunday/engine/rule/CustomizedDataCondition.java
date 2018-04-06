package com.sunday.engine.rule;

import com.sunday.engine.common.CustomizedData;
import com.sunday.engine.common.CustomizedDataContext;
import com.sunday.engine.common.Signal;

public class CustomizedDataCondition<CD extends CustomizedData> extends Condition<CustomizedDataContext<CD>> {
    private CD customizedData;

    public CustomizedDataCondition(CD cd, Signal... signals) {
        customizedData = cd;
        setSignals(signals);
    }

    public CustomizedDataCondition(CD cd, Class<Signal> signalTypeClass) {
        customizedData = cd;
        setSignals(signalTypeClass.getEnumConstants());
    }

    protected CustomizedDataCondition() {

    }

    @Override
    protected void generateMainInfo() {
        CD data = getContext().getData();
        setMainInfo(
                "Source = [" + data + "]\n" +
                        "SourceClass = [" + data.getClass().getSimpleName() + "]\n" +
                        "Signals = [" + getSignalNames() + "]"
        );
    }

    public CD getCustomizedData() {
        return customizedData;
    }


//    @Override
//    public void connectWith(SystemPort systemPort) {
////        getSignals().forEach(tracer -> {
////            systemPort.removeConnection(context.getData(), this);
////        });
////        systemPort.addConnection(context.getData(), this);
////        generateMainInfo();
//    }
//
//    @Override
//    public void disconnectWith(SystemPort systemPort) {
//
//    }

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
