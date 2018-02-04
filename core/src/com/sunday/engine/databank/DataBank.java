package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.ports.SystemPort;
import com.sunday.engine.databank.ports.UserPort;

public interface DataBank<T extends Data> {
    SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass);

    UserPort<T> getUserPort(Object user);

    void removePort(SynchronizePort synchronizePort);

}
