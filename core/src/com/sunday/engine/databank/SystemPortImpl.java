package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Target;
import com.sunday.engine.common.data.SourceClass;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SystemPortImpl extends PortImpl implements SystemPort {
    private DataBank dataBank;

    public SystemPortImpl(Object owner, DataBank dataBank, DataStorage dataStorage) {
        super(owner, dataStorage);
        this.dataBank = dataBank;
    }

    @Override
    public <D extends Data> SourceClass<D> getSourceClass(Class<D> clazz) {
        return dataStorage.getSourceClass(clazz);
    }

    @Override
    public void addConnection(Data source, Target target) {
        dataStorage.addDataConnection(this, source, target);
    }

    @Override
    public void removeConnection(Data source, Target target) {
        dataStorage.removeDataConnection(this, source, target);
    }

    @Override
    public <D extends Data> void addConnection(Class<D> clazz, Target target) {
        dataStorage.addClassConnection(this, clazz, target);
    }

    @Override
    public <D extends Data> void removeConnection(Class<D> clazz, Target target) {
        dataStorage.removeClassConnection(this, clazz, target);
    }

    @Override
    public <D extends Data> List<D> getDataList(Predicate<D> predicate) {
        return (List<D>) dataStorage.getDataList(this).stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public <D extends Data> List<Class<D>> getDataClassList() {
        return dataStorage.getDataClassList(this);
    }

    @Override
    public <D extends Data> List<D> instancesOf(Class<D> clazz) {
        return (List<D>) dataStorage.getDataInstances(clazz);
    }

    @Override
    public Port requestPort(Object owner) {
        return dataBank.getPort(owner);
    }

}
