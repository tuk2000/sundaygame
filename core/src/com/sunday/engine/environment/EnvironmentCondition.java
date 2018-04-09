package com.sunday.engine.environment;

import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Condition;

import java.util.function.Predicate;

public abstract class EnvironmentCondition<E extends EnvironmentData, EC extends EnvironmentDataContext<E>> extends Condition<EC> implements EnvironmentRelated {
    protected EnvironmentCondition() {

    }

    @Override
    protected void setSignals(Signal... signals) {
        super.setSignals(signals);
        for (Signal signal : signals) {
            addSignalPredicate(generateSignalPredicate(signal));
        }
    }

    protected Predicate<EC> generateSignalPredicate(Signal signal) {
        return ec -> ec.getSignal().equals(signal);
    }

    @Override
    protected void generateExtraInfo() {
        setExtraInfoEntry("ConditionType", "EnvironmentRelated");
    }

    @Override
    protected void generateMainInfo() {
        E e = getContext().getEnvironmentData();
        setMainInfoEntry("Source ", (e == null ? " n/a" : e.toString()));
        setMainInfoEntry("SourceClass", getContext().getEnvironmentsDataClazz().getSimpleName());
        setMainInfoEntry("Signals", getSignalNames());
    }

    public void setEnvironmentContext(EC environmentContext) {
        setContext(environmentContext);
    }

    @Override
    public void check() {
        if (isSatisfied()) {
            reaction.accept(getContext());
        }
    }
}
