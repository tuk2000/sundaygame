package com.sunday.game.Resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.sunday.game.GameFramework.DescriptorStorage;

import java.util.ArrayList;

public class ResourceDescriptorStorage implements DescriptorStorage {
    private ArrayList<AssetDescriptor> arrayLists = new ArrayList<AssetDescriptor>();
    private final Class textureClass = Texture.class;

    public ResourceDescriptorStorage() {
        arrayLists.add(new AssetDescriptor(Gdx.files.internal("buttons/buttons_small.png"), textureClass));
    }

    @Override
    public ArrayList<AssetDescriptor> getResourceDescriptor() {
        return arrayLists;
    }
}
