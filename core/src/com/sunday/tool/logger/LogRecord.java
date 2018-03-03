package com.sunday.tool.logger;


import javafx.beans.property.SimpleStringProperty;

public class LogRecord {
    SimpleStringProperty type;
    SimpleStringProperty tag;
    SimpleStringProperty content;

    public LogRecord(LogType type, String tag, String content) {
        this.type = new SimpleStringProperty(type.name());
        this.tag = new SimpleStringProperty(tag);
        this.content = new SimpleStringProperty(content);
    }

    public String getType() {
        return type.get();
    }

    public String getTag() {
        return tag.get();
    }

    public String getContent() {
        return content.get();
    }
}
