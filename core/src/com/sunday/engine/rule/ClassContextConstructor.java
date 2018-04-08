package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.ContextBankImpl;

public class ClassContextConstructor {
    private ContextBank contextBank = new ContextBankImpl();

    public boolean accept(Condition condition) {
        return condition instanceof ClassCondition;
    }

    public <RC extends Context> void construct(ClassCondition<RC> condition) {
        Class<? extends Data> clazz = condition.getSensedClass();
        if (!contextBank.hasClassContext(clazz)) {
            ClassContext<RC> classContext = new ClassContext<>(clazz);
            contextBank.addClassContext(classContext);
            condition.setClassContext(classContext);
        }
    }
}
