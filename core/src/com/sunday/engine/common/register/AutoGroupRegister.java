package com.sunday.engine.common.register;

import java.util.List;
import java.util.function.Function;

public class AutoGroupRegister<K, V> extends AutoRegister<K, V, GroupRegister<K, V>> implements UsingMultiValue<K, V> {
    public AutoGroupRegister(Function<V, K> keyCalculateFunc) {
        super(new GroupRegister(), keyCalculateFunc);
    }

    @Override
    public List<V> getValues(K k) {
        return getOriginalRegister().getValues(k);
    }

    @Override
    public void deregisterKey(K k) {
        getOriginalRegister().deregisterKey(k);
    }
}
