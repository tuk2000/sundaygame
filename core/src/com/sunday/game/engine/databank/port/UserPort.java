package com.sunday.game.engine.databank.port;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.databank.synchronize.SynchronizeTerminal;

import java.util.List;

public interface UserPort<T extends Data> extends SynchronizeTerminal {
    List<T> getInstances(Class<T> clazz);

    DataHolder getDataHolder(T t);
}
