package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.*;
import com.sunday.engine.databank.storage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class DataStorage<T extends Data> implements Disposable {
    private DataInstanceRegister<T> dataInstanceRegister = new DataInstanceRegister();
    private DataConnectionRegister<T> dataConnectionRegister = new DataConnectionRegister<>();
    private SourceClassRegister<T> sourceClassRegister = new SourceClassRegister<>();
    private SourceClassConnectionRegister sourceClassConnectionRegister = new SourceClassConnectionRegister();
    private PortContentRegisters portContentRegisters = new PortContentRegisters();


    protected DataStorage() {

    }


    protected List<Class<T>> getDataClasses() {
        return dataInstanceRegister.getKeys();
    }

    protected List<T> getDataInstances(Class<T> clazz) {
        return dataInstanceRegister.getValues(clazz);
    }

    protected void addDataInstance(Port port, T t) {
        dataInstanceRegister.register(t);
        portContentRegisters.registerData(port, t);
        solve(t, DataSignal.Add);
    }

    protected void deleteDataInstance(Port port, T t) {
        portContentRegisters.deregisterData(port, t);
        dataInstanceRegister.deregister(t);
        dataConnectionRegister.deregisterKey(t);
        solve(t, DataSignal.Deletion);
    }

    protected List<T> getDataList(Port port) {
        return (List<T>) portContentRegisters.getPortDataRegister().getValues(port);
    }

    protected <T extends Data> List<Class<T>> getDataClassList(Port port) {
        List<Class<T>> result = new ArrayList<>();
        getDataList(port).stream().collect(Collectors.groupingBy(data -> data.getClass())).keySet().forEach(clazz -> result.add((Class<T>) clazz));
        return result;
    }

    protected void destroyPort(Port port) {
        portContentRegisters.destroy(port);
    }

    protected void solve(T t, Signal signal) {
        Class<T> clazz = (Class<T>) t.getClass();
        SourceClass sourceClass = sourceClassRegister.getSourceClass(clazz);
        sourceClass.setSensedData(t);
        sourceClassConnectionRegister.getValues(sourceClass).forEach(connection -> {
            connection.target.notify(signal);
        });
        dataConnectionRegister.getValues(t).forEach(connection -> connection.target.notify(signal));
    }

    public void addDataConnection(Port port, T source, Target target) {
        Connection connection = new Connection<>(source, target);
        dataConnectionRegister.register(connection);
        portContentRegisters.registerConnection(port, connection);
    }

    public void removeDataConnection(Port port, T source, Target target) {
        if (dataConnectionRegister.hasKey(source)) {
            dataConnectionRegister.getValues(source).forEach(connection -> {
                if (connection.target.equals(target)) {
                    dataConnectionRegister.deregister(source, connection);
                    portContentRegisters.deregisterConnection(port, connection);
                }
            });
        }
    }

    public void addClassConnection(Port port, SourceClass sourceClass, Target target) {
        ClassConnection classConnection = new ClassConnection(sourceClass, target);
        sourceClassConnectionRegister.register(classConnection);
        portContentRegisters.registerConnection(port, classConnection);
    }

    public void removeClassConnection(Port port, SourceClass sourceClass, Target target) {
        sourceClassConnectionRegister.getValues(sourceClass).forEach(classConnection -> {
            if (classConnection.target.equals(target)) {
                sourceClassConnectionRegister.deregister(sourceClass, classConnection);
                portContentRegisters.deregisterConnection(port, classConnection);
            }
        });
    }

    public SourceClass<T> getSourceClass(Class<T> clazz) {
        return sourceClassRegister.getSourceClass(clazz);
    }

    @Override
    public void dispose() {
        dataInstanceRegister.getKeys().stream().forEach(key -> dataInstanceRegister.deregisterKey(key));
        dataConnectionRegister.getKeys().stream().forEach(key -> dataConnectionRegister.deregisterKey(key));
        sourceClassRegister.getKeys().stream().forEach(key -> sourceClassRegister.deregisterKey(key));
        sourceClassConnectionRegister.getKeys().stream().forEach(key -> sourceClassConnectionRegister.deregisterKey(key));
        portContentRegisters.dispose();
    }
}
