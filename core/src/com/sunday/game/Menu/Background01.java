package com.sunday.game.Menu;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.sunday.game.World.GamePlay;

public class Background01{
    //We will design the background here .. each level will have new design
    AssetManager assets =  new AssetManager();
    public void Test(){
        assets.load("bg/bg.png",Texture.class);
    }

}
