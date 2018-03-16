package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoGroupRegister;

public class DataInstanceRegister<D extends Data> extends AutoGroupRegister<Class<D>, D> {

    public DataInstanceRegister() {
        super(d -> (Class<D>) d.getClass());
    }
}
