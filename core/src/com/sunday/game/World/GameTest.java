package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.World.Animation.AnimationSetting;
import com.sunday.game.World.Sprites.HeroSprite;
import com.sunday.game.World.Sprites.SawSprite;

public class GameTest extends FocusedScreen {
    private static final float SecondPerFrame = 1 / 60f;

    private Batch batch;
    private HeroSprite heroSprite;
    private SawSprite sawSprite;


    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.LEFT:
                    heroSprite.translateX(-20);
                    break;
                case Input.Keys.RIGHT:
                    heroSprite.translateX(20);
                    break;
                case Input.Keys.UP:
                    heroSprite.translateY(20);
                    break;
                case Input.Keys.DOWN:
                    heroSprite.translateY(-20);
                    break;
            }
            return true;
        }
    };

    public GameTest() {
        batch = new SpriteBatch();
        heroSprite = new HeroSprite();
        sawSprite = new SawSprite();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        AnimationSetting.DeltaTime += Gdx.graphics.getDeltaTime();
        batch.begin();
        heroSprite.draw(batch);
        sawSprite.draw(batch);
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
        return inputAdapter;
    }
}
