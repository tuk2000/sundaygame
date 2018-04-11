package com.sunday.engine.common;

import java.util.List;
import java.util.function.BiConsumer;

public interface Register<K, V> {
    List<K> getKeys();

    K keyOf(V v);

    boolean hasKey(K k);

    boolean hasValue(V v);

    void register(K k, V v);

    void deregister(K k, V v);

    void deregisterKey(K k);

    void foreachPaar(BiConsumer<K, V> biConsumer);
}
