package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import com.sunday.game.GameFramework.TestTool.ToolExtender;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectMonitor implements ToolExtender {

    private HashMap<Class, ArrayList<Object>> clsToObjMap = new HashMap<>();
    private static ObjectMonitor objectMonitor = new ObjectMonitor();

    private ObjectMonitor() {
    }

    public static ObjectMonitor getInstance() {
        return objectMonitor;
    }

    public void StopMonitorObject(Object object) {
        Class clazz = object.getClass();
        clsToObjMap.get(clazz).remove(object);
        if (clsToObjMap.get(clazz).size() == 0) {
            clsToObjMap.remove(clazz);
        }
        updateContent();
    }

    public void MonitorObject(Object object) {
        Class clazz = object.getClass();
        if (clsToObjMap.containsKey(clazz)) {
            clsToObjMap.get(clazz).add(object);
        } else {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(object);
            clsToObjMap.put(clazz, arrayList);
        }
        updateContent();
    }

    private ObjectMonitorPanel monitorForm;

    @Override
    public void updateContent() {
        monitorForm.updateView();
    }

    @Override
    public <T  extends JComponent> void setContentPanel(T frame) {
        monitorForm = (ObjectMonitorPanel) frame;
        monitorForm.useTableModel(new ObjectTableModel(clsToObjMap));
    }

    @Override
    public <T extends JComponent> T getContentPanel() {
        return (T) monitorForm;
    }
}
