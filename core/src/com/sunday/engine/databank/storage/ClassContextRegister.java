package com.sunday.engine.databank.storage;

import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoMappingRegister;

import java.util.function.Function;

public class ClassContextRegister<K extends Data, V extends ClassContext<K>> extends AutoMappingRegister<Class<K>, V> {
    public ClassContextRegister() {
        super(new Function<V, Class<K>>() {
            @Override
            public Class<K> apply(V v) {
                return v.getSensedClass();
            }
        });
    }
}
