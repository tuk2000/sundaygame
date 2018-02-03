package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;

public interface SynchronizePort<T extends Data> {
    void addDataSynchronize(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor);

    void deleteDataSynchronize(SynchronizeCondition synchronizeCondition);

    void synchronize(T t, DataOperation dataOperation);
}
