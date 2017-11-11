package com.sunday.tool.GameMonitor;

import com.badlogic.gdx.Gdx;
import com.sunday.tool.ToolExtender;

public class GameMonitor extends ToolExtender<GameMonitorPanel> {
    private GameData gameData = new GameData();

    public void updateGameData() {
        gameData.DeltaTime = Gdx.graphics.getDeltaTime();
        gameData.FramePerSecond = Gdx.graphics.getFramesPerSecond();
        gameData.FrameId = Gdx.graphics.getFrameId();
        gameData.MemoryUsage = Gdx.app.getJavaHeap() / 1024;
        updateContentView();
    }

    @Override
    public Object getContentData() {
        return gameData;
    }
}
