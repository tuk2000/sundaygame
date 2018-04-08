package com.sunday.engine.rule;

public interface ContextConstructor<C extends Condition> {
    boolean accept(Condition condition);

    void construct(C condition);
}
