package com.sunday.engine.common.register;

import java.util.List;

public interface UsingMultiValue<K, V> {
    List<V> getValues(K k);

    void deregisterKey(K k);
}
