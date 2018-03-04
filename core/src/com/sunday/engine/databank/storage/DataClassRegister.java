package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoGroupRegister;
import com.sunday.engine.databank.register.GroupRegister;

import java.util.function.Function;

public class DataClassRegister<D extends Data> extends AutoGroupRegister<Class<D>, D> {

    public DataClassRegister() {
        super(new GroupRegister<Class<D>, D>(), new Function<D, Class<D>>() {
            @Override
            public Class apply(D d) {
                return d.getClass();
            }
        });
    }
}
