package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

import java.util.function.Consumer;

public interface Reaction<T extends Data> extends Consumer<T> {

}
