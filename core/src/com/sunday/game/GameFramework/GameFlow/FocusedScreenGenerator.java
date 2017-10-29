package com.sunday.game.GameFramework.GameFlow;

import com.sunday.game.GameFramework.FocusedScreen;

import java.util.ArrayList;

public interface FocusedScreenGenerator {
    /*  generateFocusedScreen should only be called by GameFlowManager */
    FocusedScreen generateFocusedScreen(GameStatus gameStatus);

    ArrayList<GameStatus> enumGameStatus();
}
