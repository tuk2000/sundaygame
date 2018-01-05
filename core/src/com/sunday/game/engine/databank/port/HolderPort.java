package com.sunday.game.engine.databank.port;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.databank.synchronize.SynchronizeTerminal;

public interface HolderPort<T extends Data> extends SynchronizeTerminal {

    void addDataInstance(T t);

    void deleteDataInstance(T t);
}
