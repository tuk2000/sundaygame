package com.sunday.game.GameFramework.TestTool.GameMonitor;

import com.badlogic.gdx.Gdx;
import com.sunday.game.GameFramework.TestTool.ToolExtender;

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
