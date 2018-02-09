package com.sunday.tool.datamonitor;

import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.HashMap;

public class DataMonitor extends ToolExtender<DataMonitorUIController> {

    private HashMap<Class, ArrayList<MonitoredData>> clsToObjMap = new HashMap<>();
    private ArrayList<MonitoredData> objectsBuffer = new ArrayList<>();

    private MonitoredData constructMonitoredObject(Object object) {
        return new MonitoredData(object.getClass().getName(), object.toString());
    }

    public void MonitorObject(Object object) {
        MonitoredData monitoredObject = constructMonitoredObject(object);
        Class clazz = object.getClass();
        if (clsToObjMap.containsKey(clazz)) {
            clsToObjMap.get(clazz).add(monitoredObject);
        } else {
            ArrayList<MonitoredData> arrayList = new ArrayList<>();
            arrayList.add(monitoredObject);
            clsToObjMap.put(clazz, arrayList);
        }

        objectsBuffer.add(monitoredObject);
        if (getController() != null) {
            objectsBuffer.forEach(e -> {
                getController().addMonitoredObject(e);
            });
            objectsBuffer.clear();
        }
    }

    public void StopMonitorObject(Object object) {
        Class clazz = object.getClass();
        for (MonitoredData monitoredObject : clsToObjMap.get(clazz)) {
            if (monitoredObject.getObjectName().equals(object.toString())) {
                getController().removeMonitoredObject(monitoredObject);
                break;
            }
        }
        if (clsToObjMap.get(clazz).size() == 0) {
            clsToObjMap.remove(clazz);
        }
    }

}
