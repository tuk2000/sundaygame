package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

public interface DataHolderPort {
    void addDataSynchronize(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor);

    void deleteDataSynchronize(SynchronizeCondition synchronizeCondition);

    void addDataInstance(Data data);

    void deleteDataInstance(Data data);
}
