package com.sunday.game.GameFramework.TestTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestTool {
    private static Map<Class<?>, ArrayList<Object>> clsToObjMap = new HashMap<Class<?>, ArrayList<Object>>();
    private static Map<Class<?>, ToolForm> clsToFormMap = new HashMap<Class<?>, ToolForm>();
    private static TestTool tool;

    private TestTool() {

    }

    public static void StopMonitorObject(Class<?>cls, Object obj){
        clsToObjMap.get(cls).remove(obj);
        clsToFormMap.get(cls).setTableModel(new TestDataTableModel(cls, clsToObjMap.get(cls)));
        clsToFormMap.get(cls).updateTable();
    }

    public static void MonitorObject(Class<?> cls, Object obj) {
        if (tool == null) {
            tool = new TestTool();
        }
        if (clsToObjMap.containsKey(cls)) {
            clsToObjMap.get(cls).add(obj);
            clsToFormMap.get(cls).setTableModel(new TestDataTableModel(cls, clsToObjMap.get(cls)));
            clsToFormMap.get(cls).updateTable();
        } else {
            ArrayList<Object> arrayList = new ArrayList<Object>();
            arrayList.add(obj);
            clsToFormMap.put(cls, new ToolForm(new TestDataTableModel(cls, arrayList)));
            clsToObjMap.put(cls, arrayList);
        }
    }
}
