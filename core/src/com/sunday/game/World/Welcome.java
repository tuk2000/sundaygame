package com.sunday.game.World;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.GameFramework.InputReciver;


public class Welcome extends Game implements InputReciver {
    //SpriteBatch batch;
    Screen currentGame;
    InputReciver currentReciver;
    GamePlay gamePlay;
    GameIntro gameIntro;

    public Welcome(GameStatus gameStatus) {
        switch (gameStatus) {
            case Loading:

                break;
            case Setting:
                break;
            case Intro:
                gameIntro = new GameIntro();
                currentGame = gameIntro;
                currentReciver = gameIntro;
                break;
            case InGame:
                gamePlay = new GamePlay();
                currentGame = gamePlay;
                currentReciver = gamePlay;
                break;
        }


    }

    @Override
    public void create() {
        //batch = new SpriteBatch();
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        System.out.println(System.nanoTime() + " Welcome.create() was called by " + name);
        //setScreen(gamePlay);
        setScreen(currentGame);
    }

    @Override
    public void render() {
        super.render();
    }

    public void dispose () {
        currentGame.dispose();
    }


    @Override
    public InputAdapter getInputAdapter() {
        return currentReciver.getInputAdapter();
    }


}
