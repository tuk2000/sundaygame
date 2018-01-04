package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

import java.util.HashMap;
import java.util.Map;

public class DataStorageImpl<T extends Data> implements DataStorage<T> {
    private Map<DataHolder, DataHolderPort> holderPortsMap = new HashMap<>();
    private Map<DataUser, DataUserPort> userPortsMap = new HashMap<>();
    private DataSynchronize dataSynchronize = new DataSynchronize();
    private DataClassStorage<T> dataClassStorage = new DataClassStorage<>();


    public void synchronize() {
        dataSynchronize.synchronize();
    }

    @Override
    public DataHolderPort getDataHolderPort(DataHolder dataHolder) {
        if (holderPortsMap.containsKey(dataHolder)) {
            return holderPortsMap.get(dataHolder);
        } else {
            DataHolderPort dataHolderPort = new DataHolderPortImpl(dataClassStorage, dataSynchronize);
            holderPortsMap.put(dataHolder, dataHolderPort);
            return dataHolderPort;
        }
    }


    @Override
    public DataUserPort<T> getDataUerPort(DataUser<T> dataUser) {
        if (userPortsMap.containsKey(dataUser)) {
            return userPortsMap.get(dataUser);
        } else {
            DataUserPort dataUserPort = new DataUserPortImpl(dataClassStorage, dataSynchronize);
            userPortsMap.put(dataUser, dataUserPort);
            return dataUserPort;
        }
    }

    @Override
    public void removePort(DataHolder dataHolder) {

    }

    @Override
    public void removePort(DataUser dataUser) {

    }
}
