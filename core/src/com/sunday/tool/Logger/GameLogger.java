package com.sunday.tool.Logger;

import com.badlogic.gdx.ApplicationLogger;

import java.util.ArrayList;

public class GameLogger implements ApplicationLogger {
    //    public static final int LOG_NONE = 0;
//    public static final int LOG_DEBUG = 3;
//    public static final int LOG_INFO = 2;
//    public static final int LOG_ERROR = 1;

    private ArrayList<LogMessage> logs = new ArrayList<>();

    @Override
    public void log(String tag, String message) {
        logs.add(new LogMessage(LogType.NONE, tag, message));
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.NONE, tag, message));
    }

    @Override
    public void error(String tag, String message) {
        logs.add(new LogMessage(LogType.ERROR, tag, message));
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.ERROR, tag, message));
    }

    @Override
    public void debug(String tag, String message) {
        logs.add(new LogMessage(LogType.DEBUG, tag, message));
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.DEBUG, tag, message));
    }

}
