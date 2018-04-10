package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.common.data.SourceClass;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.storage.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class DataStorage<D extends Data> implements Disposable {
    private DataInstanceRegister<D> dataInstanceRegister = new DataInstanceRegister<>();
    private DataConnectionRegister<D> dataConnectionRegister = new DataConnectionRegister<>();
    private SourceClassRegister<D> sourceClassRegister = new SourceClassRegister<>();
    private SourceClassConnectionRegister sourceClassConnectionRegister = new SourceClassConnectionRegister();
    private PortContentRegisters portContentRegisters = new PortContentRegisters();


    protected DataStorage() {

    }


    protected List<Class<D>> getDataClasses() {
        return dataInstanceRegister.getKeys();
    }

    protected List<D> getDataInstances(Class<D> clazz) {
        return dataInstanceRegister.getValues(clazz);
    }

    protected void addDataInstance(Port port, D d) {
        dataInstanceRegister.register(d);
        portContentRegisters.registerData(port, d);
        solve(d, DataSignal.Add);
    }

    protected void removeDataInstance(Port port, D d) {
        portContentRegisters.deregisterData(port, d);
        dataInstanceRegister.deregister(d);
        dataConnectionRegister.deregisterKey(d);
        solve(d, DataSignal.Deletion);
    }

    protected List<D> getDataList(Port port) {
        return (List<D>) portContentRegisters.getPortDataRegister().getValues(port);
    }

    protected <T extends Data> List<Class<T>> getDataClassList(Port port) {
        List<Class<T>> result = new ArrayList<>();
        getDataList(port).stream().collect(Collectors.groupingBy(data -> data.getClass())).keySet().forEach(clazz -> result.add((Class<T>) clazz));
        return result;
    }

    protected void destroyPort(Port port) {
        portContentRegisters.destroy(port);
    }

    protected void solve(D d, Signal signal) {
        Class<D> clazz = (Class<D>) d.getClass();
        SourceClass sourceClass = sourceClassRegister.getSourceClass(clazz);
        sourceClassConnectionRegister.getValues(sourceClass).forEach(connection -> {
            connection.target.notify(d, signal);
        });
        dataConnectionRegister.getValues(d).forEach(connection -> connection.target.notify(d, signal));
    }

    public void addDataConnection(Port port, D source, Target target) {
        Connection connection = new Connection<>(source, target);
        dataConnectionRegister.register(connection);
        portContentRegisters.registerConnection(port, connection);
    }

    public void removeDataConnection(Port port, D source, Target target) {
        if (dataConnectionRegister.hasKey(source)) {
            dataConnectionRegister.getValues(source).forEach(connection -> {
                if (connection.target.equals(target)) {
                    dataConnectionRegister.deregister(source, connection);
                    portContentRegisters.deregisterConnection(port, connection);
                }
            });
        }
    }

    public void addClassConnection(Port port, Class<D> clazz, Target target) {
        SourceClass<D> sourceClass = getSourceClass(clazz);
        ClassConnection classConnection = new ClassConnection(sourceClass, target);
        sourceClassConnectionRegister.register(classConnection);
        portContentRegisters.registerConnection(port, classConnection);
    }

    public void removeClassConnection(Port port, Class<D> clazz, Target target) {
        SourceClass<D> sourceClass = getSourceClass(clazz);
        sourceClassConnectionRegister.getValues(sourceClass).forEach(classConnection -> {
            if (classConnection.target.equals(target)) {
                sourceClassConnectionRegister.deregister(sourceClass, classConnection);
                portContentRegisters.deregisterConnection(port, classConnection);
            }
        });
    }

    public SourceClass<D> getSourceClass(Class<D> clazz) {
        return sourceClassRegister.getSourceClass(clazz);
    }

    @Override
    public void dispose() {
        dataInstanceRegister.getKeys().forEach(key -> dataInstanceRegister.deregisterKey(key));
        dataConnectionRegister.getKeys().forEach(key -> dataConnectionRegister.deregisterKey(key));
        sourceClassRegister.getKeys().forEach(key -> sourceClassRegister.deregisterKey(key));
        sourceClassConnectionRegister.getKeys().forEach(key -> sourceClassConnectionRegister.deregisterKey(key));
        portContentRegisters.dispose();
    }
}
