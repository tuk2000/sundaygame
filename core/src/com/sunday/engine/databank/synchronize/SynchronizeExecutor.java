package com.sunday.engine.databank.synchronize;

import com.sunday.engine.common.Data;

public interface SynchronizeExecutor<T extends Data> {
    void execute(SynchronizeEvent<T> synchronizeEvent);
}
