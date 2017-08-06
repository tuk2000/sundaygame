package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.GameFramework.InputReceiver;


public class Welcome extends Game implements InputReceiver {
    private SpriteBatch batch;
    private BitmapFont font;
    private Screen currentGame;
    private InputReceiver currentReciver;
    private GamePlay gamePlay;
    private GameIntro gameIntro;
    private GameTest gameTest;
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
            case Test:
                gameTest = new GameTest();
                currentGame = gameTest;
                currentReciver = gameTest;
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

        //ClearColor White and it needs to be  defined only once
        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render() {

        //clear the screen before anything rendered
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render the currentScreen
        super.render();
        //render additional information at tops layer of all
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
