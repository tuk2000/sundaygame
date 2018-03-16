package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.register.AutoGroupRegister;


public class DataConnectionRegister<K extends Data> extends AutoGroupRegister<K, Connection<K>> {

    public DataConnectionRegister() {
        super(v -> v.source);
    }

}
