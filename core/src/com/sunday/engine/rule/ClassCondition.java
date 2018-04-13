package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.propertyholder.SystemRelated;

public class ClassCondition<RC extends DataContext> extends Condition<ClassContext<RC>> implements SystemRelated {
    protected SignalCondition<RC> signalCondition = new SignalCondition<>(dataContext->dataContext.getSignal());
    private Class<? extends Data> sensedClass;

    public <D extends Data, S extends Signal> ClassCondition(Class<D> clazz, S... signals) {
        sensedClass = clazz;
        signalCondition.setSignals(signals);
    }

    public <D extends Data, S extends Signal> ClassCondition(Class<D> clazz, Class<S> signalTypeClass) {
        sensedClass = clazz;
        signalCondition.setSignals(signalTypeClass.getEnumConstants());
    }

    public <D extends Data> Class<D> getSensedClass() {
        return (Class<D>) sensedClass;
    }

    @Override
    protected void generateMainInfo(ClassContext<RC> classContext) {
        setMainInfoEntry("Source", sensedClass.getName());
        setMainInfoEntry("SourceClass ", sensedClass.getClass().getSimpleName());
        setMainInfoEntry("Signals ", signalCondition.getSignalNames());
    }

    protected void generateExtraInfo(ClassContext<RC> classContext) {
        setExtraInfoEntry("ConditionType", "ClassCondition");
        setExtraInfoEntry("SensedClass", sensedClass.getSimpleName());
    }

    @Override
    public boolean test(ClassContext<RC> classContext) {
        return signalCondition.test(classContext.getFocusedContext());
    }
}
