package com.sunday.game.GameFramework.TestTool.ScreenLoader;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.TestTool.ToolExtender;

import java.util.ArrayList;

public class ScreenLoader extends ToolExtender<ScreenLoaderPanel> {
    private ArrayList<GameStatus> arrayList = new ArrayList<>();

    public void loadGameStatusEnum(ArrayList<GameStatus> arrayList) {
        this.arrayList.clear();
        arrayList.forEach(e -> {
            this.arrayList.add(e);
        });
        updateContentView();
    }

    @Override
    public Object getContentData() {
        return arrayList;
    }
}
