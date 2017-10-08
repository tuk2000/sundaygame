package com.sunday.game.GameFramework.GameFlow;

import com.sunday.game.GameFramework.FocusedScreen;

public interface GameFlowExecutor {

    FocusedScreen getCurrentFocusedScreen();

    void setCurrentFocusedScreen(FocusedScreen focusedScreen);

}
