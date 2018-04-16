package com.sunday.engine.common.register;

public interface UsingSingleValue<K, V> {
    V getValue(K k);
}
