package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassCondition<D extends Data, DC extends Context> extends Condition<ClassContext<D, DC>> implements SystemRelated {
    private Class<D> sensedClass;

    public <S extends Signal> ClassCondition(Class<D> clazz, S... signals) {
        sensedClass = clazz;
        setSignals(signals);
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    public <S extends Signal> ClassCondition(Class<D> clazz, Class<S> signalTypeClass) {
        sensedClass = clazz;
        setSignals(signalTypeClass.getEnumConstants());
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    @Override
    protected void generateMainInfo() {
        setMainInfo("Source = [" + sensedClass + "]\n" +
                "SourceClass = [" + sensedClass.getClass().getSimpleName() + "]\n" +
                "Signals = [" + getSignalNames() + "]");
    }
}
