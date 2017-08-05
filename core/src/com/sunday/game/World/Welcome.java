package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.GameFramework.InputReciver;


public class Welcome extends Game implements InputReciver {
    SpriteBatch batch;
    BitmapFont font;
    Screen currentGame;
    InputReciver currentReciver;
    GamePlay gamePlay;
    GameIntro gameIntro;
    private float duration;
    private long memeoryUsage;
    private boolean isToDestroyed;


    public Welcome(GameStatus gameStatus) {
        duration = 1.0f;
        isToDestroyed = false;
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
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(1, 0, 0, 1);
        String name = Thread.currentThread().getStackTrace()[3].getClassName();
        System.out.println(System.nanoTime() + " Welcome.create() was called by " + name);
        //setScreen(gamePlay);
        setScreen(currentGame);
        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        batch.begin();
        font.draw(batch, "Infos", 0, 720);
        font.draw(batch, "DeltaTime : " + Gdx.graphics.getDeltaTime(), 0, 700);
        font.draw(batch, "Framerate : " + Gdx.graphics.getFramesPerSecond(), 0, 680);
        font.draw(batch, "FrameId : " + Gdx.graphics.getFrameId(), 0, 660);
        duration += Gdx.graphics.getDeltaTime();
        if (!isToDestroyed & duration > 1.0) {
            memeoryUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
            duration = 0;
        }
        font.draw(batch, "MemoryUsage : " + memeoryUsage + " KB", 0, 640);
        batch.end();
    }

    @Override
    public void dispose() {
        isToDestroyed = true;
        super.dispose();
        currentGame.dispose();
        font.dispose();
        batch.dispose();
    }


    @Override
    public InputAdapter getInputAdapter() {
        return currentReciver.getInputAdapter();
    }


}
