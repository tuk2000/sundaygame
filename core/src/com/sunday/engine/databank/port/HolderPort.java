package com.sunday.engine.databank.port;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.synchronize.SynchronizeTerminal;

public interface HolderPort<T extends Data> extends SynchronizeTerminal {

    void addDataInstance(T t);

    void deleteDataInstance(T t);
}
