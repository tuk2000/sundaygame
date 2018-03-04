package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Connection;
import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoGroupRegister;
import com.sunday.engine.databank.register.GroupRegister;

import java.util.function.Function;


public class ConnectionRegister<K extends Data, V extends Connection> extends AutoGroupRegister<K, V> {

    public ConnectionRegister() {
        super(new GroupRegister(), new Function<V, K>() {
            @Override
            public K apply(V v) {
                return (K) v.source;
            }
        });
    }

}
