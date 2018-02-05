package com.sunday.engine.databank;


import com.sunday.engine.common.Data;

import java.util.List;
import java.util.function.Predicate;

public interface SystemPort<T extends Data> extends Port<T> {

    List<T> getDataList(Predicate<T> predicate);

    List<Class<T>> getDataClassList();

    List<T> searchInDataBank(Class<T> clazz);
}