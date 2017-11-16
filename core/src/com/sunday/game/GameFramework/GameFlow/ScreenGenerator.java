package com.sunday.game.GameFramework.GameFlow;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public interface ScreenGenerator {
    /*  generateScreen should only be called by GameFlowManager */
    Screen generateScreen(GameStatus gameStatus);

    ArrayList<GameStatus> enumGameStatus();
}
