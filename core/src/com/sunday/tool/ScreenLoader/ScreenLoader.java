package com.sunday.tool.ScreenLoader;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.tool.ToolExtender;

import java.util.ArrayList;

public class ScreenLoader extends ToolExtender<ScreenLoaderController> {
    private ArrayList<GameStatus> arrayList = new ArrayList<>();

    public void loadGameStatusEnum(ArrayList<GameStatus> arrayList) {
        this.arrayList.clear();
        arrayList.forEach(e -> {
            this.arrayList.add(e);
        });

        getController().loadGameStatusEnum(arrayList);

    }

    public void initGameStatusEnum() {
        getController().loadGameStatusEnum(arrayList);
    }

}
