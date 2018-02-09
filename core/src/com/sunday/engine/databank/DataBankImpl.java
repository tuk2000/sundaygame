package com.sunday.engine.databank;

import com.sunday.engine.common.Data;

import java.util.HashMap;
import java.util.Map;

public class DataBankImpl<T extends Data> implements DataBank<T> {
    private Map<Class<? extends SubSystem>, SystemPort> systemPortMap = new HashMap<>();
    private Map<Object, Port> userPortsMap = new HashMap<>();
    private DataStorage dataStorage;

    public DataBankImpl() {
        dataStorage = new DataStorage();
    }

    @Override
    public SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass) {
        if (!systemPortMap.containsKey(subSystemClass)) {
            systemPortMap.put(subSystemClass, new SystemPortImpl(dataStorage));
        }
        SystemPort systemPort = systemPortMap.get(subSystemClass);
        dataStorage.addPort(systemPort);
        return systemPort;
    }

    @Override
    public Port<T> getPort(Object user) {
        if (!userPortsMap.containsKey(user)) {
            userPortsMap.put(user, new PortImpl(dataStorage));
        }
        Port port = userPortsMap.get(user);
        dataStorage.addPort(port);
        return port;
    }

    @Override
    public void removePort(Port port) {
        for (Class clazz : systemPortMap.keySet()) {
            if (systemPortMap.get(clazz).equals(port)) {
                systemPortMap.remove(clazz);
            }
        }
        for (Object user : userPortsMap.keySet()) {
            if (userPortsMap.get(user).equals(port)) {
                userPortsMap.remove(user);
            }
        }
        dataStorage.removePort(port);
    }
}
