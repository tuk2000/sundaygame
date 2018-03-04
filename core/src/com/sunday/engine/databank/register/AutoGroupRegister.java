package com.sunday.engine.databank.register;

import java.util.List;
import java.util.function.Function;

public class AutoGroupRegister<K, V> extends AutoRegister<K, V, GroupRegister<K, V>> implements UsingMultiValue<K, V> {
    public AutoGroupRegister(GroupRegister<K, V> originalRegister, Function<V, K> keyCalculateFunc) {
        super(originalRegister, keyCalculateFunc);
    }

    @Override
    public List<V> getValues(K k) {
        return getOriginalRegister().getValues(k);
    }
}
