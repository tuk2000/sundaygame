package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import com.sunday.game.GameFramework.TestTool.ToolExtender;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectMonitor extends ToolExtender<ObjectMonitorPanel> {

    private HashMap<Class, ArrayList<Object>> clsToObjMap = new HashMap<>();

    public void MonitorObject(Object object) {
        Class clazz = object.getClass();
        if (clsToObjMap.containsKey(clazz)) {
            clsToObjMap.get(clazz).add(object);
        } else {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(object);
            clsToObjMap.put(clazz, arrayList);
        }
        updateContentView();
    }

    public void StopMonitorObject(Object object) {
        Class clazz = object.getClass();
        clsToObjMap.get(clazz).remove(object);
        if (clsToObjMap.get(clazz).size() == 0) {
            clsToObjMap.remove(clazz);
        }
        updateContentView();
    }

    @Override
    public Object getContentData() {
        return new ObjectTableModel(clsToObjMap);
    }
}
