package com.sunday.engine.databank;

import com.sunday.engine.common.Data;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SystemPortImpl<T extends Data> extends PortImpl<T> implements SystemPort<T> {
    private DataStorage dataStorage;

    public SystemPortImpl(DataStorage dataStorage) {
        super(dataStorage);
        this.dataStorage = dataStorage;
    }

    @Override
    public List<T> getDataList(Predicate<T> predicate) {
        return (List<T>) dataStorage.getDataList(this).stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Class<T>> getDataClassList() {
        return dataStorage.getDataClassList(this);
    }

    @Override
    public List<T> searchInDataBank(Class<T> clazz) {
        return dataStorage.getInstances(clazz);
    }

}
