package com.sunday.engine.databank.storage;

import com.sunday.engine.common.CustomizedDataContext;
import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoMappingRegister;

import java.util.function.Function;

public class DataConetxtRegister<K extends Data, V extends CustomizedDataContext<K>> extends AutoMappingRegister<K, V> {
    public DataConetxtRegister() {
        super(new Function<V, K>() {
            @Override
            public K apply(V v) {
                return v.getData();
            }
        });
    }
}
