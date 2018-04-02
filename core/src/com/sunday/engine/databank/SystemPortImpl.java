package com.sunday.engine.databank;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.common.Target;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SystemPortImpl<T extends Data> extends PortImpl<T> implements SystemPort<T> {
    private DataBank dataBank;

    public SystemPortImpl(Object owner, DataBank dataBank, DataStorage dataStorage) {
        super(owner, dataStorage);
        this.dataBank = dataBank;
    }

    @Override
    public SourceClass<T> getSourceClass(Class<T> clazz) {
        return dataStorage.getSourceClass(clazz);
    }

    @Override
    public void addContext(Context context) {

    }

    @Override
    public void removeContext(Context context) {

    }

    @Override
    public Context getContext(T data) {
        return null;
    }

    @Override
    public void addContext(Class<T> clazz) {

    }

    @Override
    public void removeContext(Class<T> clazz) {

    }

    @Override
    public Context getContext(Class<T> clazz) {
        return null;
    }

    @Override
    public void addConnection(T source, Target target) {
        dataStorage.addDataConnection(this, source, target);
    }

    @Override
    public void removeConnection(T source, Target target) {
        dataStorage.removeDataConnection(this, source, target);
    }

    @Override
    public void addConnection(Class<T> clazz, Target target) {
        dataStorage.addClassConnection(this, clazz, target);
    }

    @Override
    public void removeConnection(Class<T> clazz, Target target) {
        dataStorage.removeClassConnection(this, clazz, target);
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
    public List<T> instancesOf(Class<T> clazz) {
        return (List<T>) dataStorage.getDataInstances(clazz);
    }

    @Override
    public Port requestPort(Object owner) {
        return dataBank.getPort(owner);
    }

}
