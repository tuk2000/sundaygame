package com.sunday.engine.databank.ports;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.SynchronizePort;

public interface UserPort<T extends Data> extends SynchronizePort {
    void addDataInstance(T t);

    void deleteDataInstance(T t);
}
