package com.sunday.game.GameFramework.TestTool.Logger;

import com.badlogic.gdx.ApplicationLogger;
import com.sunday.game.GameFramework.TestTool.ToolExtender;

import javax.swing.*;
import java.util.ArrayList;

;

public class GameLogger implements ApplicationLogger ,ToolExtender {
//    public static final int LOG_NONE = 0;
//    public static final int LOG_DEBUG = 3;
//    public static final int LOG_INFO = 2;
//    public static final int LOG_ERROR = 1;
    private static GameLogger gameLogger;

    public static GameLogger getInstance() {
        if(gameLogger==null){
            gameLogger=new GameLogger();
        }
        return gameLogger;
    }


    private ArrayList<LogMessage> logs = new ArrayList<>();


    private GameLogger() {
    }


    /**
     * Logs a message with a tag
     *
     * @param tag
     * @param message
     */
    @Override
    public void log(String tag, String message) {
        logs.add(new LogMessage(LogType.NONE, tag, message));
        updateContent();
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
        updateContent();
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
        updateContent();
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
        updateContent();
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
        updateContent();
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
        updateContent();
    }

    private LogPanel logPanel;

    @Override
    public void updateContent() {
        logPanel.updateView();
    }

    @Override
    public <T  extends JComponent> void setContentPanel(T frame) {
        logPanel = (LogPanel) frame;
        logPanel.useListMode(new LogListModel(logs));
    }

    @Override
    public  <T extends JComponent> T getContentPanel() {
        return (T) logPanel;
    }
}
