package com.sunday.game.framework.display;

import com.badlogic.gdx.Screen;
import com.sunday.game.framework.gameflow.GameStatus;

import java.util.ArrayList;

public interface ScreenGenerator {
    /*  generateScreen should only be called by GameFlowManager */
    Screen generateScreen(GameStatus gameStatus);

    ArrayList<GameStatus> enumGameStatus();
}
