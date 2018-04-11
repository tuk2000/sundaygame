package com.sunday.engine.rule;

public interface ContextConstructor<C extends Condition> {
    boolean test(Condition condition);

    void construct(C condition);
}
