package com.sunday.engine.rule;

import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;

@FunctionalInterface
public interface ClassReaction<RC extends DataContext> extends Reaction<ClassContext<RC>> {
    default void accept(ClassContext<RC> classContext) {
        accept(classContext.getFocusedContext());
    }

    void accept(RC context);
}