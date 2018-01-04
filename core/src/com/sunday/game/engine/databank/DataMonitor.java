package com.sunday.game.engine.databank;

import java.util.ArrayList;
import java.util.List;

public class DataMonitor<T> {

    private List<DataEventListener<T>> listeners;

    public DataMonitor() {
        listeners = new ArrayList<>();
    }

    public void registerListener(DataEventListener<T> dataEventListener) {
        listeners.add(dataEventListener);
    }

    public void removeListener(DataEventListener<T> dataEventListener) {
        if (listeners.contains(dataEventListener)) {
            listeners.remove(dataEventListener);
        }
    }


}
