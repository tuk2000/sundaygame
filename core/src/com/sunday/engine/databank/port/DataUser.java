package com.sunday.engine.databank.port;

import com.sunday.engine.common.Data;

public class DataUser<T extends Data> {
    private Object user;
    private Class<T> clazz;

    public DataUser(Object user, Class<T> clazz) {
        this.user = user;
        this.clazz = clazz;
    }

    public Object getRealUser() {
        return user;
    }

    public Class<T> getClazz() {
        return clazz;
    }
}
