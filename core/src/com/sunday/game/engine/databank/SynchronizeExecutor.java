package com.sunday.game.engine.databank;

public class SynchronizeExecutor implements Runnable {
    public Runnable runnable;

    public SynchronizeExecutor(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
