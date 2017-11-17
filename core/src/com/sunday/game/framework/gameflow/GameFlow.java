package com.sunday.game.framework.gameflow;


import com.badlogic.gdx.Screen;

/*
    class gameflow preserves the history of GameStatus and Screen like stack
    most useful is the getCurrentGameFlow() , it tells the presents GameStatus and Screen
    and after called backToPreviewGameFlow() currentGameFlow will automatic be set als its preview gameflow.
    the next gameflow is in this situation not necessary .
 */
class GameFlow {
    private GameFlow preview;//the preview of firstGameFlow is null
    public GameStatus status;
    public Screen screen;

    private static GameFlow firstGameFlow = new GameFlow();
    private static GameFlow currentGameFlow;

    public static GameStatus getCurrentGameStatus() {
        return currentGameFlow.status;
    }

    public static void setCurrentScreen(Screen screen) {
        currentGameFlow.screen = screen;
    }

    public static Screen getCurrentScreen() {
        return currentGameFlow.screen;
    }

    public static void setFirstGameFlow(GameStatus status, Screen screen) {
        while (currentGameFlow != null) {
            backToPreviewGameFlow();
        }
        firstGameFlow.preview = null;
        firstGameFlow.status = status;
        firstGameFlow.screen = screen;
        currentGameFlow = firstGameFlow;
    }

    public static void appendGameFlow(GameStatus status, Screen screen) {
        GameFlow gameFlow = new GameFlow();
        gameFlow.status = status;
        gameFlow.screen = screen;
        gameFlow.preview = currentGameFlow;
        currentGameFlow = gameFlow;
    }

    public static void backToPreviewGameFlow() {
        if (currentGameFlow == firstGameFlow) {
            firstGameFlow.preview = null;
            firstGameFlow.status = null;
            firstGameFlow.screen = null;
            currentGameFlow = null;
        } else {
            GameFlow previewFlow = currentGameFlow.preview;
            currentGameFlow.preview = null;
            currentGameFlow.status = null;
            currentGameFlow.screen = null;
            currentGameFlow = previewFlow;
        }
    }
}
