package com.sunday.engine.databank;


import com.sunday.engine.common.Data;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.common.Target;

import java.util.List;
import java.util.function.Predicate;

public interface SystemPort<T extends Data> extends Port<T> {
    SourceClass<T> getSourceClass(Class<T> clazz);

    void addConnection(T source, Target target);

    void removeConnection(T source, Target target);

    void addConnection(Class<T> clazz, Target target);

    void removeConnection(Class<T> clazz, Target target);

    List<T> getDataList(Predicate<T> predicate);

    List<Class<T>> getDataClassList();

    List<T> instancesOf(Class<T> clazz);

    Port requestPort(Object owner);
}