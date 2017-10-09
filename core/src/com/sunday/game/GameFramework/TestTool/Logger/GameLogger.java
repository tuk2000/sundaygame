package com.sunday.game.GameFramework.TestTool.Logger;

import com.badlogic.gdx.ApplicationLogger;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

;

public class GameLogger implements ApplicationLogger {
    public static final int LOG_NONE = 0;
    public static final int LOG_DEBUG = 3;
    public static final int LOG_INFO = 2;
    public static final int LOG_ERROR = 1;

    private LogForm logForm;

    private ArrayList<LogMessage> logs = new ArrayList<>();


    public GameLogger() {
        logForm = new LogForm();
        logForm.setLocation(0, 200);
    }


    private void UpdateLogForm() {
        logForm.UpdateList(new LogListModel(logs));
    }

    /**
     * Logs a message with a tag
     *
     * @param tag
     * @param message
     */
    @Override
    public void log(String tag, String message) {
        System.out.println(tag + ": " + message);
        logs.add(new LogMessage(LogType.NONE, tag, message));
        UpdateLogForm();
    }

    /**
     * Logs a message and exception with a tag
     *
     * @param tag
     * @param message
     * @param exception
     */
    @Override
    public void log(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.NONE, tag, message));
        UpdateLogForm();
    }

    /**
     * Logs an error message with a tag
     *
     * @param tag
     * @param message
     */
    @Override
    public void error(String tag, String message) {
        logs.add(new LogMessage(LogType.ERROR, tag, message));
        UpdateLogForm();
    }

    /**
     * Logs an error message and exception with a tag
     *
     * @param tag
     * @param message
     * @param exception
     */
    @Override
    public void error(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.ERROR, tag, message));
        UpdateLogForm();
    }

    /**
     * Logs a debug message with a tag
     *
     * @param tag
     * @param message
     */
    @Override
    public void debug(String tag, String message) {
        logs.add(new LogMessage(LogType.DEBUG, tag, message));
        UpdateLogForm();
    }

    /**
     * Logs a debug message and exception with a tag
     *
     * @param tag
     * @param message
     * @param exception
     */
    @Override
    public void debug(String tag, String message, Throwable exception) {
        logs.add(new LogMessage(LogType.DEBUG, tag, message));
        UpdateLogForm();
    }
}
