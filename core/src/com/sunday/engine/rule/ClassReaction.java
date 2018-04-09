package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.context.ClassContext;

@FunctionalInterface
public interface ClassReaction<RC extends Context> extends Reaction<ClassContext<RC>> {
    default void accept(ClassContext<RC> classContext) {
        accept(classContext.getFocusedContext());
    }

    void accept(RC context);
}