package com.sunday.tool.ObjectMonitor;

import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectMonitor extends ToolExtender<ObjectMonitorController> {

    private HashMap<Class, ArrayList<MonitoredObject>> clsToObjMap = new HashMap<>();
    private ArrayList<MonitoredObject> objectsBuffer = new ArrayList<>();

    private MonitoredObject constructMonitoredObject(Object object) {
        return new MonitoredObject(object.getClass().getName(), object.toString());
    }

    public void MonitorObject(Object object) {
        MonitoredObject monitoredObject = constructMonitoredObject(object);
        Class clazz = object.getClass();
        if (clsToObjMap.containsKey(clazz)) {
            clsToObjMap.get(clazz).add(monitoredObject);
        } else {
            ArrayList<MonitoredObject> arrayList = new ArrayList<>();
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
        for (MonitoredObject monitoredObject : clsToObjMap.get(clazz)) {
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
