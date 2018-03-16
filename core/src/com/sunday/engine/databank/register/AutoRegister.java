package com.sunday.engine.databank.register;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class AutoRegister<K, V, R extends Register> implements Register<K, V> {
    private Function<V, K> keyCalculateFunc;
    private R originalRegister;

    public AutoRegister(R originalRegister, Function<V, K> keyCalculateFunc) {
        this.originalRegister = originalRegister;
        this.keyCalculateFunc = keyCalculateFunc;
    }

    public R getOriginalRegister() {
        return originalRegister;
    }

    public void register(V v) {
        K k = keyCalculateFunc.apply(v);
        register(k, v);
    }

    public void deregister(V v) {
        K k = keyCalculateFunc.apply(v);
        deregister(k, v);
    }

    @Override
    public List<K> getKeys() {
        return originalRegister.getKeys();
    }

    @Override
    public K keyOf(V v) {
        return keyCalculateFunc.apply(v);
    }

    @Override
    public boolean hasKey(K k) {
        return originalRegister.hasKey(k);
    }

    @Override
    public boolean hasValue(V v) {
        return originalRegister.hasValue(v);
    }

    @Override
    public void register(K k, V v) {
        originalRegister.register(k, v);
    }

    @Override
    public void deregister(K k, V v) {
        originalRegister.deregister(k, v);
    }

    @Override
    public void foreachPaar(BiConsumer<K, V> biConsumer) {
        originalRegister.foreachPaar(biConsumer);
    }
}
