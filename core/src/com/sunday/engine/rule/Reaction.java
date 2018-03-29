package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

import java.util.function.BiConsumer;

public interface Reaction<T extends Data, S extends Signal> extends BiConsumer<T, S> {

}
