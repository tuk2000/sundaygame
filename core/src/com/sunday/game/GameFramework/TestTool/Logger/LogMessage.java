package com.sunday.game.GameFramework.TestTool.Logger;

class LogMessage {
    LogType type;
    String tag;
    String content;

    LogMessage(LogType type, String tag, String content) {
        this.type = type;
        this.tag = tag;
        this.content = content;
    }
}
