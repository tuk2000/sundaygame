package com.sunday.engine.databank;

import com.sunday.engine.common.Connection;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.storage.ConnectionRegister;
import com.sunday.engine.databank.storage.DataClassRegister;
import com.sunday.engine.databank.storage.PortDataRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

class DataStorage<T extends Data> {

    private PortDataRegister portDataRegister = new PortDataRegister();
    private DataClassRegister<T> dataClassRegister = new DataClassRegister();
    private ConnectionRegister<T, Connection> connectionRegister = new ConnectionRegister();

    protected DataStorage() {

    }

    protected List<Class<T>> getDataClasses() {
        return dataClassRegister.getKeys();
    }

    protected List<T> getInstances(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        dataClassRegister.foreachPaar(new BiConsumer<Class<T>, T>() {
            @Override
            public void accept(Class<T> clazz, T t) {
                result.add(t);
            }
        });
        return result;
    }

    protected void addDataInstance(Port port, T t) {
        if (t instanceof Connection) {
            connectionRegister.register((Connection) t);
        }
        portDataRegister.register(port, t);
        dataClassRegister.register(t);
        solve(t, DataSignal.Add);
    }

    protected void solve(T t, Signal signal) {
        connectionRegister.getValues(t).stream().filter(
                connection -> {
                    boolean isSensor = connection.source instanceof ClassSensor;
                    boolean isSensorClass = false;
                    ClassSensor classSensor;
                    if (isSensor) {
                        classSensor = (ClassSensor) connection.source;
                        isSensorClass = t.getClass().equals(classSensor.getSensedClass());
                        if (isSensorClass) {
                            classSensor.setSensedInstance(t);
                        }
                    }
                    return connection.source.equals(t) || isSensor & isSensorClass;
                }
        ).map(c -> c.target)
                .forEach(target -> target.notify(signal));
    }

    protected void deleteDataInstance(Port port, T t) {
        if (t instanceof Connection) {
            connectionRegister.deregister((Connection) t);
        }
        portDataRegister.deregister(port, t);
        dataClassRegister.deregister(t);
        solve(t, DataSignal.Deletion);
    }

    protected List<T> getDataList(Port port) {
        return (List<T>) portDataRegister.getValues(port);
    }

    protected <T extends Data> List<Class<T>> getDataClassList(Port port) {
        List<Class<T>> result = new ArrayList<>();
        getDataList(port).stream().collect(Collectors.groupingBy(data -> data.getClass())).keySet().forEach(clazz->result.add((Class<T>) clazz));
        return result;
    }

    protected void destroyPort(Port port) {
        getDataList(port).forEach(data -> {
            deleteDataInstance(port, data);
        });
    }
}
