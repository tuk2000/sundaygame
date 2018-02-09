package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sunday.game.framework.GameFramework;


public class GameIntro implements Screen {
    private Stage stage;
    private Button start;
    private Button exit;
    private Button setting;
    private Button test;
    private Button tiledMap;
    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private TextureRegion[][] textureRegions;
    private Texture buttonTextures;

    public GameIntro() {
        stage = new Stage();
        font = new BitmapFont();
        buttonTextures = GameFramework.Resource.getAsset("buttons/buttons_small.png");
        textureRegions = TextureRegion.split(buttonTextures, 150, 75);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(textureRegions[0][0]);
        textButtonStyle.down = new TextureRegionDrawable(textureRegions[0][1]);
        textButtonStyle.checked = new TextureRegionDrawable(textureRegions[0][2]);

        start = new TextButton("START", textButtonStyle);
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameFramework.GameFlow.setCurrentScreen(GamePlay.class);
            }
        });
        exit = new TextButton("EXIT", textButtonStyle);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        setting = new TextButton("SETTING", textButtonStyle);
        setting.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameFramework.GameFlow.setCurrentScreen(GameSetting.class);
            }
        });
        /*test = new TextButton("TEST", textButtonStyle);
        test.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               GameFlowManager.getInstance().setCurrentScreen(GameStatus.Test);
            }
        });*/
        test = new TextButton("Tiled Map", textButtonStyle);
        test.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameFramework.GameFlow.setCurrentScreen(TiledGameMap.class);
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        float height = stage.getHeight() / 5;
        float width_mid = stage.getWidth() / 2;
        float button_height_mid = start.getHeight() / 2;
        float button_width_mid = start.getWidth() / 2;
        float button_width_mid_all = width_mid - button_width_mid;

        start.setPosition(button_width_mid_all, 4 * height - button_height_mid);

        exit.setPosition(button_width_mid_all, 3 * height - button_height_mid);

        setting.setPosition(button_width_mid_all, 2 * height - button_height_mid);

        test.setPosition(button_width_mid_all, 1 * height - button_height_mid);

        stage.addActor(start);
        stage.addActor(exit);
        stage.addActor(setting);
        stage.addActor(test);
//        going to test
//        GameFramework.switchToolOnOrOff();
//        GameFramework.GameFlow.setCurrentScreen(GameStatus.Test);
    }

    @Override
    public void render(float delta) {
        stage.draw();
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
        font.dispose();
        stage.dispose();
    }

}
