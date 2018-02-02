package com.sunday.engine.databank.port;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.synchronize.SynchronizeTerminal;

import java.util.List;

public interface UserPort<T extends Data> extends SynchronizeTerminal {
    List<T> getInstances(Class<T> clazz);

    DataHolder getDataHolder(T t);
}
