package com.sunday.game.PhysicalEmulation;

import java.util.Timer;
import java.util.TimerTask;

public class TimeSlot {
    public boolean TickTock = true;
    private TimerTask timerTask;
    private static Timer timer = new Timer();

    public TimeSlot(long period) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TickTock = !TickTock;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, period);
        timerTask.run();
    }

    public void stop() {
        timerTask.cancel();
        TickTock = false;
    }

    public void resume() {
        timerTask.run();
    }

}
