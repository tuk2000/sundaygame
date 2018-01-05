package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.databank.port.UserPort;

public interface DataBank<T extends Data> {

    HolderPort getHolderPort(Object realHolder);

    UserPort<T> getUserPort(Object realUser, Class<T> clazz);

    void removePort(Object realRole);

}
