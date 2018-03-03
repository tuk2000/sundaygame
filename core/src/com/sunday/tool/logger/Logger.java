package com.sunday.tool.logger;

import com.badlogic.gdx.ApplicationLogger;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class Logger extends ToolExtender<LoggerUIController> implements ApplicationLogger {
//    public static final int LOG_NONE = 0;
//    public static final int LOG_DEBUG = 3;
//    public static final int LOG_INFO = 2;
//    public static final int LOG_ERROR = 1;

    public Logger() {
        uiControllerBuffer.addBuffer(LogRecord.class, true, new BiConsumer<LoggerUIController, LogRecord>() {
            @Override
            public void accept(LoggerUIController loggerUIController, LogRecord logRecord) {
                loggerUIController.newLogRecord(logRecord);
            }
        });
    }

    private void disposeLogMessage(LogType type, String tag, String message) {
        LogRecord logRecord = new LogRecord(LogType.NONE, tag, message);
        uiControllerBuffer.addInstance(logRecord);
        flushBuffer();
    }

    @Override
    public void log(String tag, String message) {
        disposeLogMessage(LogType.NONE, tag, message);
    }

    @Override
    public void log(String tag, String message, Throwable exception) {
        disposeLogMessage(LogType.NONE, tag, message);
    }

    @Override
    public void error(String tag, String message) {
        disposeLogMessage(LogType.ERROR, tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable exception) {
        disposeLogMessage(LogType.ERROR, tag, message);
    }

    @Override
    public void debug(String tag, String message) {
        disposeLogMessage(LogType.DEBUG, tag, message);
    }

    @Override
    public void debug(String tag, String message, Throwable exception) {
        disposeLogMessage(LogType.DEBUG, tag, message);
    }

}
