package com.sunday.game.engine.databank.port;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.databank.DataStorage;
import com.sunday.game.engine.databank.synchronize.SynchronizeManager;

import java.util.HashMap;
import java.util.Map;

public class PortManager<T extends Data> {
    private Map<DataHolder, HolderPort> holderPortsMap = new HashMap<>();
    private Map<DataUser, UserPort> userPortsMap = new HashMap<>();

    private SynchronizeManager synchronizeManager = new SynchronizeManager();
    private DataStorage<T> dataStorage = new DataStorage<>(synchronizeManager);

    public HolderPort getDataHolderPort(Object realHolder) {
        for (DataHolder holder : holderPortsMap.keySet()) {
            if (holder.getRealHolder().equals(realHolder)) {
                return holderPortsMap.get(holder);
            }
        }
        DataHolder dataHolder = new DataHolder(realHolder);
        HolderPort dataHolderPort = new HolderPortImpl(dataHolder, dataStorage, synchronizeManager);
        holderPortsMap.put(dataHolder, dataHolderPort);
        return dataHolderPort;
    }

    public UserPort<T> getDataUserPort(Object realUser, Class<T> clazz) {

        for (DataUser user : userPortsMap.keySet()) {
            if (user.getRealUser().equals(realUser)) {
                return userPortsMap.get(user);
            }
        }
        DataUser<T> dataUser = new DataUser<>(realUser, clazz);
        UserPort dataUserPort = new UserPortImpl(dataUser, dataStorage, synchronizeManager);
        userPortsMap.put(dataUser, dataUserPort);
        return dataUserPort;
    }

    private void removePort(DataHolder dataHolder) {

    }

    private void removePort(DataUser dataUser) {

    }

    public void removePort(Object realRole) {
    }
}
