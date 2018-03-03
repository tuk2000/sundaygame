package com.sunday.tool.logger;

import com.sunday.tool.ToolExtenderUIController;

public interface LoggerUIController extends ToolExtenderUIController {
    void newLogRecord(LogRecord logRecord);
}
