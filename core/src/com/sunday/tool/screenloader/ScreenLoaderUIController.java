package com.sunday.tool.screenloader;

import com.sunday.game.framework.gameflow.GameStatus;
import com.sunday.tool.ToolExtenderUIController;

import java.util.ArrayList;

public interface ScreenLoaderUIController extends ToolExtenderUIController {
    void loadGameStatusEnum(ArrayList<GameStatus> arrayList);
}
