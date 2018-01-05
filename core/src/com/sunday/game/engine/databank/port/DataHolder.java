package com.sunday.game.engine.databank.port;

public class DataHolder {
    private Object holder;

    public DataHolder(Object holder) {
        this.holder = holder;
    }

    public Object getRealHolder() {
        return holder;
    }
}
