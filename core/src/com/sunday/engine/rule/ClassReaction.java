package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.context.ClassContext;

public abstract class ClassReaction<RC extends Context> implements Reaction<ClassContext<RC>> {
    @Override
    public void accept(ClassContext<RC> classContext) {
        accept(classContext.getFocusedContext());
    }

    public abstract void accept(RC context);
}