package com.sunday.game.GameFramework.TestTool.ObjectMonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ObjectMonitor {
    private static Map<Class<?>, ArrayList<Object>> clsToObjMap = new HashMap<Class<?>, ArrayList<Object>>();
    private static Map<Class<?>, ObjectMonitorForm> clsToFormMap = new HashMap<Class<?>, ObjectMonitorForm>();


    public static void StopMonitorObject(Class<?> cls, Object obj) {
        clsToObjMap.get(cls).remove(obj);
        if(clsToObjMap.get(cls).size()==0){
            clsToObjMap.remove(cls);
            clsToFormMap.remove(cls);
        }else{
            clsToFormMap.get(cls).setTableModel(new ObjectTableModel(cls, clsToObjMap.get(cls)));
        }
    }

    public static void MonitorObject(Class<?> cls, Object obj) {

        if (clsToObjMap.containsKey(cls)) {
            clsToObjMap.get(cls).add(obj);
            clsToFormMap.get(cls).setTableModel(new ObjectTableModel(cls, clsToObjMap.get(cls)));
        } else {
            ArrayList<Object> arrayList = new ArrayList<Object>();
            arrayList.add(obj);
            clsToFormMap.put(cls, new ObjectMonitorForm(new ObjectTableModel(cls, arrayList)));
            clsToObjMap.put(cls, arrayList);
        }
    }
}
