package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.ports.SystemPort;
import com.sunday.engine.databank.ports.SystemPortImpl;
import com.sunday.engine.databank.ports.UserPort;
import com.sunday.engine.databank.ports.UserPortImpl;
import com.sunday.engine.databank.synchronize.SynchronizeManager;

import java.util.HashMap;
import java.util.Map;

public class DataBankImpl<T extends Data> implements DataBank<T> {
    private Map<Class<? extends SubSystem>, SystemPort> systemPortMap = new HashMap<>();
    private Map<Object, UserPort> userPortsMap = new HashMap<>();
    private SynchronizeManager synchronizeManager;
    private DataStorage dataStorage;

    public DataBankImpl() {
        synchronizeManager = new SynchronizeManager();
        dataStorage = new DataStorage(synchronizeManager);
    }

    @Override
    public SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass) {
        if (!systemPortMap.containsKey(subSystemClass)) {
            systemPortMap.put(subSystemClass, new SystemPortImpl(dataStorage, synchronizeManager));
        }
        SystemPort systemPort = systemPortMap.get(subSystemClass);
        dataStorage.addPort(systemPort);
        return systemPort;
    }

    @Override
    public UserPort<T> getUserPort(Object user) {
        if (!userPortsMap.containsKey(user)) {
            userPortsMap.put(user, new UserPortImpl(dataStorage, synchronizeManager));
        }
        UserPort userPort = userPortsMap.get(user);
        dataStorage.addPort(userPort);
        return userPort;
    }

    @Override
    public void removePort(SynchronizePort synchronizePort) {
        for (Class clazz : systemPortMap.keySet()) {
            if (systemPortMap.get(clazz).equals(synchronizePort)) {
                systemPortMap.remove(clazz);
            }
        }
        for (Object user : userPortsMap.keySet()) {
            if (userPortsMap.get(user).equals(synchronizePort)) {
                userPortsMap.remove(user);
            }
        }
        dataStorage.removePort(synchronizePort);
    }
}
