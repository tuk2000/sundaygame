package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.databank.SystemPort;


public class ClassCondition<T extends Data, S extends Signal> extends Condition<SourceClass<T>, S> {

    private Class<T> sensedSourceClass;

    public ClassCondition(Class<T> clazz, S... signals) {
        sensedSourceClass = clazz;
        setSignals(signals);
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    public ClassCondition(Class<T> clazz, Class<S> signalTypeClass) {
        sensedSourceClass = clazz;
        setSignals(signalTypeClass.getEnumConstants());
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        setData(systemPort.getSourceClass(sensedSourceClass));
        systemPort.addConnection(sensedSourceClass, this);
        generateMainInfo();
    }

    @Override
    public void notify(Data data, Signal signal) {
        if (getSignals().contains(signal))
            getReaction().accept(data, signal);
    }
}
