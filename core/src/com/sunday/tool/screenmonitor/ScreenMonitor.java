package com.sunday.tool.screenmonitor;

import com.badlogic.gdx.Screen;
import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ScreenMonitor extends ToolExtender<ScreenMonitorUIController> {
    private ArrayList<ScreenRecord> screenRecords = new ArrayList<>();

    public ScreenMonitor() {
        uiControllerBuffer.addBuffer(ScreenRecord.class, true, new BiConsumer<ScreenMonitorUIController, ScreenRecord>() {
            @Override
            public void accept(ScreenMonitorUIController screenMonitorUIController, ScreenRecord screenRecord) {
                screenMonitorUIController.addScreenRecord(screenRecord);
            }
        });
        uiControllerBuffer.addBuffer(ScreenRecord.class, false, new BiConsumer<ScreenMonitorUIController, ScreenRecord>() {
            @Override
            public void accept(ScreenMonitorUIController screenMonitorUIController, ScreenRecord screenRecord) {
                screenMonitorUIController.removeScreenRecord(screenRecord);
            }
        });
        uiControllerBuffer.addBuffer(String.class, true, new BiConsumer<ScreenMonitorUIController, String>() {
            @Override
            public void accept(ScreenMonitorUIController screenMonitorUIController, String s) {
                screenMonitorUIController.loadScreenClass(s);
            }
        });
    }

    public void setScreenNameList(List<String> list) {
        list.forEach(className -> {
            uiControllerBuffer.addInstance(className);
        });
        flushBuffer();
    }

    public void monitorInstance(Screen screen) {
        ScreenRecord screenRecord = new ScreenRecord(screen.getClass().getSimpleName(), screen.toString());
        screenRecords.add(screenRecord);
        uiControllerBuffer.addInstance(screenRecord);
        flushBuffer();
    }

    public void forgetInstance(Screen screen) {
        for (ScreenRecord screenRecord : screenRecords) {
            if (screenRecord.getInstanceName().equals(screen.toString())) {
                screenRecords.remove(screenRecord);
                uiControllerBuffer.removeInstance(screenRecord);
                break;
            }
        }
        flushBuffer();
    }
}
