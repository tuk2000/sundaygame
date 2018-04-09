package com.sunday.engine.rule;

import java.util.function.Consumer;

public interface ContextConstructor<C extends Condition> extends Consumer<C> {
    boolean test(Condition condition);
}
