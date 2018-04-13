package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.databank.ContextBank;

public class ClassContextConstructor {

    private ContextBank contextBank;

    public ClassContextConstructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    public boolean accept(Condition condition) {
        return condition instanceof ClassCondition;
    }

    public <RC extends DataContext> ClassContext<RC> construct(ClassCondition<RC> condition) {
        Class<? extends Data> clazz = condition.getSensedClass();
        return getClassContext(clazz);
    }

    public <RC extends DataContext> ClassContext<RC> getClassContext(Class<? extends Data> clazz) {
        ClassContext<RC> classContext;
        if (contextBank.hasClassContext(clazz)) {
            classContext = contextBank.getClassContext(clazz);
        } else {
            classContext = new ClassContext<>(clazz);
            contextBank.addClassContext(classContext);
        }
        return classContext;
    }
}
