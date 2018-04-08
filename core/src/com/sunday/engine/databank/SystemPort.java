package com.sunday.engine.databank;


import com.sunday.engine.common.Data;
import com.sunday.engine.common.Target;
import com.sunday.engine.common.data.SourceClass;

import java.util.List;
import java.util.function.Predicate;

public interface SystemPort extends Port {
    <D extends Data> SourceClass<D> getSourceClass(Class<D> clazz);

    void addConnection(Data source, Target target);

    void removeConnection(Data source, Target target);

    <D extends Data> void addConnection(Class<D> clazz, Target target);

    <D extends Data> void removeConnection(Class<D> clazz, Target target);

    <D extends Data> List<D> getDataList(Predicate<D> predicate);

    <D extends Data> List<Class<D>> getDataClassList();

    <D extends Data> List<D> instancesOf(Class<D> clazz);

    Port requestPort(Object owner);
}