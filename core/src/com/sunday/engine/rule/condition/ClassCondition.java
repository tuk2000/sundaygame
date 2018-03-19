package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Tracer;


public class ClassCondition<T extends Data> extends Condition<SourceClass<T>> {

    private Class<T> sensedSourceClass;

    public ClassCondition(Class<T> clazz, Signal... signals) {
        sensedSourceClass = clazz;
        setSignals(signals);
        setExtraInfo("Type = [ClassCondition]\n" +
                "SensedSourceClass=[" + clazz.getSimpleName() + "]");
    }

    @Override
    protected boolean isSatisfied() {
        return true;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        setData(systemPort.getSourceClass(sensedSourceClass));
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
