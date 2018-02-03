package com.sunday.engine.databank.ports;


import com.sunday.engine.common.Data;
import com.sunday.engine.databank.SynchronizePort;

import java.util.List;

public interface SystemPort<T extends Data> extends SynchronizePort<T> {
    void addDataInstance(T t);

    void deleteDataInstance(T t);

    List<T> getDataInstances(Class<T> clazz);
}