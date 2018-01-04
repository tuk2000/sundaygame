package com.sunday.game.engine.databank;

import java.util.List;

public interface DataEventListener<T> {
    void DataModified(List<T> list);

    void DataDeleted(List<T> list);

    void DataAdded(List<T> list);
}
