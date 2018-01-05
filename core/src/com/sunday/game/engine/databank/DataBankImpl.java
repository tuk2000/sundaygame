package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.databank.port.PortManager;
import com.sunday.game.engine.databank.port.UserPort;

public class DataBankImpl<T extends Data> implements DataBank<T> {
    private PortManager portManager = new PortManager();

    @Override
    public HolderPort getHolderPort(Object realHolder) {
        return portManager.getDataHolderPort(realHolder);
    }

    @Override
    public UserPort<T> getUserPort(Object realUser, Class<T> clazz) {
        return portManager.getDataUserPort(realUser, clazz);
    }

    @Override
    public void removePort(Object realRole) {
        portManager.removePort(realRole);
    }
}
