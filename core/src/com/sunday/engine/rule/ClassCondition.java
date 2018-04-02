package com.sunday.engine.rule;

import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;

public class ClassCondition<D extends Data> extends Condition<ClassContext<D>> {

    public <S extends Signal> ClassCondition(Class<D> clazz, S... signals) {
        context = ContextBuilder.buildClassContext(clazz);
        setSignals(signals);
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    public <S extends Signal> ClassCondition(Class<D> clazz, Class<S> signalTypeClass) {
        context = ContextBuilder.buildClassContext(clazz);
        setSignals(signalTypeClass.getEnumConstants());
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        generateMainInfo();
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

//    @Override
//    public void notify(Data data, Signal signal) {
//        if (getSignals().contains(signal))
//            getReaction().accept(context);
//    }

    @Override
    protected void generateMainInfo() {

    }
}
