package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.sunday.game.framework.gameflow.GameStatus;
import com.sunday.game.framework.gameflow.ScreenGenerator;

import java.util.ArrayList;

public class GameScreenGenerator implements ScreenGenerator {
    @Override
    public Screen generateScreen(GameStatus gameStatus) {
        switch (gameStatus) {
            case Loading:
                return new GameLoading();
            case MapOfGame:
                return new TiledGameMap();
            case Intro:
                return new GameIntro();
            case Setting:
                return new GameSetting();
            case InGame:
                return new GamePlay();
            case GamePause:
                return new GamePause();
            case Test:
                return new GameTest();
            case WorldShiftStart:
                //WorldShiftStart: clear the old Game ,shift not into intro but instead into an animations
                return null;
            case WorldShiftEnd:
                //WorldShiftEnd: clear the animations ,shift into new game screen
            default:
                return null;
        }
    }

    @Override
    public ArrayList<GameStatus> enumGameStatus() {
        ArrayList<GameStatus> arrayList = new ArrayList<>();
        arrayList.add(GameStatus.Loading);
        arrayList.add(GameStatus.Intro);
        arrayList.add(GameStatus.MapOfGame);
        arrayList.add(GameStatus.Setting);
        arrayList.add(GameStatus.InGame);
        arrayList.add(GameStatus.GamePause);
        arrayList.add(GameStatus.Test);
        return arrayList;
    }
}
