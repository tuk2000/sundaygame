package com.sunday.engine.rule;

import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextUser;

public class ClassCondition<D extends Data> extends Condition<ClassContext<D>> implements SystemContextUser {
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

    @Override
    public void useSystemContext(ContextBank contextBank) {
        setContext(contextBank.getContext(sensedClass));
        generateMainInfo();
    }
}
