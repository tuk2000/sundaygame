package com.sunday.tool.Logger;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

class LogListModel<String> implements ListModel<String> {
    private ArrayList<LogMessage> logs;

    LogListModel(ArrayList<LogMessage> logs) {
        this.logs = logs;
    }

    @Override
    public int getSize() {
        return logs.size();
    }

    @Override
    public String getElementAt(int index) {
        LogMessage logMessage = logs.get(logs.size() - 1 - index);
        StringBuilder builder = new StringBuilder();
        builder.append(logMessage.type.name());
        builder.append("-");
        builder.append(logMessage.tag);
        builder.append(": ");
        builder.append(logMessage.content);
        return (String) builder.toString();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
