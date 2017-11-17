package com.sunday.tool.screenloader;

import com.sunday.game.framework.gameflow.GameStatus;
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

}
