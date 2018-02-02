package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.port.HolderPort;
import com.sunday.engine.databank.port.UserPort;

public interface DataBank<T extends Data> {

    HolderPort getHolderPort(Object realHolder);

    UserPort<T> getUserPort(Object realUser, Class<T> clazz);

    void removePort(Object realRole);

}
