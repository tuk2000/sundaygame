package com.sunday.tool.logger;

import com.sunday.tool.ToolExtenderController;

public interface GameLoggerController extends ToolExtenderController {
    void newLogMessage(LogMessage logMessage);
}
