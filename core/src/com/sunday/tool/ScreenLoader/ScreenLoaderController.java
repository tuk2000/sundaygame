package com.sunday.tool.ScreenLoader;

import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.tool.ToolExtenderController;

import java.util.ArrayList;

public interface ScreenLoaderController extends ToolExtenderController {
    void loadGameStatusEnum( ArrayList<GameStatus> arrayList);
}
