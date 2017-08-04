package com.sunday.game.World;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sunday.game.GameFramework.GameFlowManager;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.GameFramework.InputReciver;


public class GameIntro implements Screen, InputReciver {
    Stage stage;
    Button start;
    Button exit;
    Button setting;
    Button test;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    TextureRegion[][] textureRegions;
    Texture buttonTextures;

    public GameIntro() {
        font = new BitmapFont();
        buttonTextures = new Texture("buttons/buttons_small.png");
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
                GameFlowManager.getInstance().setGameStatus(GameStatus.InGame);
            }
        });
        exit = new TextButton("EXIT", textButtonStyle);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.exit(0);
            }
        });
        setting = new TextButton("SETTING", textButtonStyle);
        test = new TextButton("TEST", textButtonStyle);

    }

    @Override
    public void show() {
        stage = new Stage();

        float heightdivfive = stage.getHeight() / 5;
        float widthmid = stage.getWidth() / 2;
        float buttonheightmid = start.getHeight() / 2;
        float buttonwidthmid = start.getWidth() / 2;
        float buttonwidmidall = widthmid - buttonwidthmid;

        start.setPosition(buttonwidmidall, 4*heightdivfive - buttonheightmid);

        exit.setPosition(buttonwidmidall, 3 * heightdivfive - buttonheightmid);

        setting.setPosition(buttonwidmidall, 2 * heightdivfive - buttonheightmid);

        test.setPosition(buttonwidmidall, 1 * heightdivfive - buttonheightmid);

        stage.addActor(start);
        stage.addActor(exit);
        stage.addActor(setting);
        stage.addActor(test);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT );
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

    }

    @Override
    public InputAdapter getInputAdapter() {
        return stage;
    }
}
