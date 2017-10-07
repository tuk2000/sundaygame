package com.sunday.game.GameFramework;

public interface GameFlowExecutor {

    void setCurrentFocusedScreen(FocusedScreen focusedScreen);

    FocusedScreen getCurrentFocusedScreen();

    FocusedScreen generateFocusedScreen(GameStatus gameStatus);
}
