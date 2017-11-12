package com.sunday.tool.Logger;

import com.sunday.tool.ToolExtenderController;

public interface GameLoggerController extends ToolExtenderController {
    void newLogMessage(LogMessage logMessage);
}
