package com.sunday.engine.databank.register;

import java.util.function.Function;

public class AutoMappingRegister<K, V> extends AutoRegister<K, V, MappingRegister<K, V>> implements UsingSingleValue<K, V> {
    public AutoMappingRegister(MappingRegister<K, V> originalRegister, Function<V, K> keyCalculateFunc) {
        super(originalRegister, keyCalculateFunc);
    }

    @Override
    public V getValue(K k) {
        return getOriginalRegister().getValue(k);
    }
}
