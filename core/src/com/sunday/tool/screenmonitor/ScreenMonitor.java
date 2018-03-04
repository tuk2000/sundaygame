package com.sunday.tool.screenmonitor;

import com.badlogic.gdx.Screen;
import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ScreenMonitor extends ToolExtender<ScreenMonitorUIController> {
    private ArrayList<ScreenRecord> screenRecords = new ArrayList<>();

    public ScreenMonitor() {
        uiControllerBuffer.addBuffer(ScreenRecord.class, true,
                (BiConsumer<ScreenMonitorUIController, ScreenRecord>) (screenMonitorUIController, screenRecord) -> screenMonitorUIController.addScreenRecord(screenRecord));
        uiControllerBuffer.addBuffer(ScreenRecord.class, false,
                (BiConsumer<ScreenMonitorUIController, ScreenRecord>) (screenMonitorUIController, screenRecord) -> screenMonitorUIController.removeScreenRecord(screenRecord));
        uiControllerBuffer.addBuffer(String.class, true,
                (BiConsumer<ScreenMonitorUIController, String>) (screenMonitorUIController, s) -> screenMonitorUIController.loadScreenClass(s));
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
