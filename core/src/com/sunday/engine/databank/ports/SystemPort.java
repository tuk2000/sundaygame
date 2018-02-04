package com.sunday.engine.databank.ports;


import com.sunday.engine.common.Data;
import com.sunday.engine.databank.SynchronizePort;

import java.util.List;
import java.util.function.Predicate;

public interface SystemPort<T extends Data> extends SynchronizePort<T> {

    void addDataInstance(T t);

    boolean containsDataInstance(T t);

    void deleteDataInstance(T t);

    List<T> getDataList(Predicate<T> predicate);

    List<Class<T>> getDataClassList();

    List<T> searchInDataBank(Class<T> clazz);
}