package com.sunday.engine.databank.synchronize;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;

public interface SynchronizeTerminal<T extends Data> {
    void addDataSynchronize(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor);

    void deleteDataSynchronize(SynchronizeCondition synchronizeCondition);

    void synchronize(T t, DataOperation dataOperation);
}
