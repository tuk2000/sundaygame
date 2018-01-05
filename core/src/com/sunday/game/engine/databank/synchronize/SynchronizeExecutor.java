package com.sunday.game.engine.databank.synchronize;

import com.sunday.game.engine.common.Data;

public interface SynchronizeExecutor<T extends Data> {
    void execute(SynchronizeEvent<T> synchronizeEvent);
}
