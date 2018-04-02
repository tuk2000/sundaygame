package com.sunday.engine.rule;

import com.sunday.engine.common.Context;

import java.util.function.Consumer;

public interface Reaction<C extends Context> extends Consumer<C> {
}
