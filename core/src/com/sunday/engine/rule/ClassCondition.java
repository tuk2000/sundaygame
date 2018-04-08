package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassCondition<D extends Data, C extends Context> extends Condition<ClassContext<C>> implements SystemRelated {
    private Class<D> sensedClass;

    public <S extends Signal> ClassCondition(Class<D> clazz, S... signals) {
        sensedClass = clazz;
        setSignals(signals);
        generateMainInfo();
        generateExtraInfo();
    }

    public <S extends Signal> ClassCondition(Class<D> clazz, Class<S> signalTypeClass) {
        sensedClass = clazz;
        setSignals(signalTypeClass.getEnumConstants());
        generateMainInfo();
        generateExtraInfo();
    }

    public Class<D> getSensedClass() {
        return sensedClass;
    }

    public void setClassContext(ClassContext<C> context) {
        setContext(context);
    }

    @Override
    protected void generateMainInfo() {
        setMainInfoEntry("Source", sensedClass.toGenericString());
        setMainInfoEntry("SourceClass ", sensedClass.getClass().getSimpleName());
        setMainInfoEntry("Signals ", getSignalNames());
    }

    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "Class");
        setExtraInfoEntry("SensedClass", sensedClass.getSimpleName());
    }
}
