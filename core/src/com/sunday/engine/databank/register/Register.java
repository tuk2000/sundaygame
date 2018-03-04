package com.sunday.engine.databank.register;

import java.util.List;
import java.util.function.BiConsumer;

public interface Register<K, V> {
    List<K> getKeys();

    boolean hasKey(K k);

    boolean hasValue(V v);

    void register(K k, V v);

    void deregister(K k, V v);

    void foreachPaar(BiConsumer<K, V> biConsumer);
}
