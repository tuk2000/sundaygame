package com.sunday.game.GameFramework;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;


public class ResourceManager {
    private static ResourceManager resourceManager = null;
    private AssetManager assetManager;
    private DescriptorStorage descriptorStorage;

    public static ResourceManager getInstance() {
        if (resourceManager == null) {
            resourceManager = new ResourceManager();
        }
        return resourceManager;
    }

    private ResourceManager() {
        assetManager = new AssetManager();
    }

    public void loadResourceFromDescriptorStorage(DescriptorStorage descriptorStorage) {
        this.descriptorStorage = descriptorStorage;
        if (descriptorStorage != null) {
            ArrayList<AssetDescriptor> arrayList = descriptorStorage.getResourceDescriptor();
            for (AssetDescriptor e : arrayList) assetManager.load(e);
        }
    }

    public boolean isFinishLoading() {
        return descriptorStorage == null || assetManager.update();
    }

    public void makeSureFinishLoading(){
        assetManager.finishLoading();
    }

    public float getLoadingProgress() {
        return descriptorStorage == null ? 1.0f : assetManager.getProgress();
    }

    public <T> T getAsset(String path, Class<T> cls) {
        return descriptorStorage == null ? null : assetManager.get(path, cls);
    }

    public <T> T getAsset(String path){
        String format=path.substring(path.lastIndexOf(".")+1);
        Class cls=GameResourceType.getRelatedClass(format);
        return getAsset(path, (Class<T>) cls);
    }
}
