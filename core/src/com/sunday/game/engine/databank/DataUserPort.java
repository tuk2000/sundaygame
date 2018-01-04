package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

import java.util.List;

public interface DataUserPort<T extends Data> {
    List<T> getInstances(Class<T> clazz);

    void requestSynchronize(T t);

    void registerDataEventListener(Class<T> clazz,DataEventListener<T> dataEventListener);
}
