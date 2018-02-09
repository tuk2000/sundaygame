package com.sunday.engine.databank;

import com.sunday.engine.common.Data;

public interface DataBank<T extends Data> {
    SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass);

    Port<T> getPort(Object user);

    void removePort(Port port);

}
