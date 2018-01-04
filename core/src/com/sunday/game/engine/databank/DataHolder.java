package com.sunday.game.engine.databank;

public class DataHolder {
    private Object holder;

    public DataHolder(Object holder) {
        this.holder = holder;
    }

    public Object getRealHolder() {
        return holder;
    }
}
