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
    private Map<SubSystem, SystemPort> systemPortMap = new HashMap<>();
    private Map<Object, UserPort> userPortsMap = new HashMap<>();
    private SynchronizeManager synchronizeManager;
    private DataStorage dataStorage;

    public DataBankImpl() {
        synchronizeManager = new SynchronizeManager();
        dataStorage = new DataStorage(synchronizeManager);
    }

    @Override
    public SystemPort getSystemPort(SubSystem subSystem) {
        if (!systemPortMap.containsKey(subSystem)) {
            systemPortMap.put(subSystem, new SystemPortImpl(dataStorage, synchronizeManager));
        }
        return systemPortMap.get(subSystem);
    }

    @Override
    public UserPort<T> getUserPort(Object user) {
        if (!userPortsMap.containsKey(user)) {
            userPortsMap.put(user, new UserPortImpl(dataStorage, synchronizeManager));
        }
        return userPortsMap.get(user);
    }

    @Override
    public void removePort(SynchronizePort synchronizePort) {
        for (SubSystem subSystem : systemPortMap.keySet()) {
            if (systemPortMap.get(subSystem).equals(synchronizePort)) {
                systemPortMap.remove(subSystem);
            }
        }
        for (Object user : userPortsMap.keySet()) {
            if (userPortsMap.get(user).equals(synchronizePort)) {
                userPortsMap.remove(user);
            }
        }
    }
}
