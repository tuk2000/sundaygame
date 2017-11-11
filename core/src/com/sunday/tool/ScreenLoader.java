package com.sunday.tool;

import com.sunday.game.GameFramework.GameFlow.GameStatus;

import java.util.ArrayList;

public class ScreenLoader {
    private ArrayList<GameStatus> arrayList = new ArrayList<>();

    public void loadGameStatusEnum(ArrayList<GameStatus> arrayList) {
        this.arrayList.clear();
        arrayList.forEach(e -> {
            this.arrayList.add(e);
        });
    }

}
