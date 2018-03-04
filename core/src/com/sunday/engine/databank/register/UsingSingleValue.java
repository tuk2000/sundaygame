package com.sunday.engine.databank.register;

public interface UsingSingleValue<K, V> {
    V getValue(K k);
}
