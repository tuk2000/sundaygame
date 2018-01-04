package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

public interface DataStorage<T extends Data> {

    DataHolderPort getDataHolderPort(DataHolder dataHolder);

    DataUserPort<T> getDataUerPort(DataUser<T> dataUser);

    void removePort(DataHolder dataHolder);

    void removePort(DataUser dataUser);
}
