package com.sunday.engine.databank.register;

import java.util.List;

public interface UsingMultiValue<K, V> {
    List<V> getValues(K k);
}
