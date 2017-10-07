package com.sunday.game.GameFramework;

public interface GameFlowExecutor {

    FocusedScreen getCurrentFocusedScreen();

    void setCurrentFocusedScreen(FocusedScreen focusedScreen);

    FocusedScreen generateFocusedScreen(GameStatus gameStatus);
}
