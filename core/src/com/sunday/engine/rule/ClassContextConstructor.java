package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.ContextBank;

public class ClassContextConstructor {

    private ContextBank contextBank;

    public ClassContextConstructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    public boolean accept(Condition condition) {
        return condition instanceof ClassCondition;
    }

    public <RC extends Context> void construct(ClassCondition<RC> condition) {
        Class<? extends Data> clazz = condition.getSensedClass();
        ClassContext<RC> classContext;
        if (contextBank.hasClassContext(clazz)) {
            classContext = contextBank.getClassContext(clazz);
        } else {
            classContext = new ClassContext<>(clazz);
            contextBank.addClassContext(classContext);
        }
        classContext.setEvaluateConnection(condition, condition.getReaction());
        condition.generateInfoWith(classContext);
    }

    public <RC extends Context> void bind(Rule<RC> rule) {

    }
}
