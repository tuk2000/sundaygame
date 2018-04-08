package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

public interface Port {
    Object getOwner();

    void addDataInstance(Data data);

    boolean containsDataInstance(Data data);

    void removeDataInstance(Data data);

    void broadcast(Data data, Signal signal);
}
