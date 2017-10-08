package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;
import com.sunday.game.Resource.ResourceDescriptorStorage;

public class GameLoading extends FocusedScreen {
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean finishing = false;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(0, 0, 0, 1);
        GameFramework.Resource.loadResourceFromDescriptorStorage(new ResourceDescriptorStorage());
    }

    @Override
    public void render(float delta) {
        batch.begin();
        if (!finishing) {
            finishing = GameFramework.Resource.isFinishLoading();
        }
        if (finishing) {
            font.draw(batch, "LOADING GAME 100%", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2);
            GameFramework.Resource.makeSureFinishLoading();
            GameFramework.GameFlow.setGameStatus(GameStatus.Intro);
        } else {
            font.draw(batch, "LOADING GAME " + (int) (100 * GameFramework.Resource.getLoadingProgress()) + "%", Gdx.graphics.getWidth() / 2 - 80, Gdx.graphics.getHeight() / 2);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public InputAdapter getInputAdapter() {
        return new InputAdapter();
    }
}
