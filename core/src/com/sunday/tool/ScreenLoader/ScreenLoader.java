package com.sunday.tool.screenloader;

import com.sunday.tool.ToolExtender;

import java.util.ArrayList;
import java.util.List;

public class ScreenLoader extends ToolExtender<ScreenLoaderUIController> {
    private List<String> arrayList = new ArrayList<>();

    public void setScreenNameList(List<String> list) {
        this.arrayList.clear();
        this.arrayList.addAll(list);
        getController().loadScreenList(arrayList);
    }

}
