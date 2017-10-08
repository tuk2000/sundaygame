package com.sunday.game.Resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.sunday.game.GameFramework.Resouce.DescriptorStorage;
import com.sunday.game.GameFramework.Resouce.GameResourceType;

import java.io.File;
import java.util.ArrayList;

public class ResourceDescriptorStorage implements DescriptorStorage {
    private ArrayList<AssetDescriptor> arrayLists = new ArrayList<AssetDescriptor>();

    public ResourceDescriptorStorage() {
        ArrayList<String> filepaths = findAllInternalResourceFile();
        filepaths.forEach(e -> {
            FileHandle fileHandle = Gdx.files.internal(e);
            arrayLists.add(new AssetDescriptor(fileHandle, GameResourceType.getRelatedClass(fileHandle)));
        });
    }

    @Override
    public ArrayList<AssetDescriptor> getResourceDescriptor() {
        return arrayLists;
    }

    private ArrayList<String> findAllInternalResourceFile() {
        ArrayList<String> filepaths = new ArrayList<>();
        String rootPath = Gdx.files.getLocalStoragePath();
        searchFiles(new File(rootPath), filepaths);
        filepaths = trimRootPaths(filepaths, rootPath.length());
        return filterFiles(filepaths);
    }

    private void searchFiles(File rootFile, ArrayList<String> container) {
        if (rootFile.exists()) {
            if (rootFile.isDirectory()) {
                File[] files = rootFile.listFiles();
                for (File file : files) {
                    searchFiles(file, container);
                }
            } else {
                container.add(rootFile.getPath());
            }
        }
    }

    private ArrayList<String> trimRootPaths(ArrayList<String> container, int rootLength) {
        ArrayList<String> container_tmp = new ArrayList<>();
        for (String path : container) {
            path = path.substring(rootLength);
            container_tmp.add(path);
        }
        return container_tmp;
    }

    private ArrayList<String> filterFiles(ArrayList<String> container) {
        ArrayList<String> container_tmp = new ArrayList<>();
        for (String path : container) {
            if (path.endsWith(".png") || path.endsWith(".mp3") || path.endsWith(".ogg")) {
                container_tmp.add(path);
            }
        }
        return container_tmp;
    }
}
