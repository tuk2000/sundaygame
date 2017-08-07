package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum PlayerSprite {
    PLAYER{
        Texture playerTxt = new Texture(Gdx.files.internal("player_img/player_01.png"));
    }
}
