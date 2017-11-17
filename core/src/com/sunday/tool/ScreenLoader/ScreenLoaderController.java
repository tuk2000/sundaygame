package com.sunday.tool.screenloader;

import com.sunday.game.framework.gameflow.GameStatus;
import com.sunday.tool.ToolExtenderController;

import java.util.ArrayList;

public interface ScreenLoaderController extends ToolExtenderController {
    void loadGameStatusEnum(ArrayList<GameStatus> arrayList);
}
