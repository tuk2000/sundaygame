package com.sunday.game.GameFramework;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public enum GameResourceType {
    None(Object.class), Texture(Texture.class), Music(Music.class), Sound(Sound.class);
    public Class cls;

    GameResourceType(Class cls) {
        this.cls = cls;
    }


    public static Class getRelatedClass(FileHandle file) {
        return getRelatedClass(file.extension());
    }

    public static Class getRelatedClass(String format) {
        GameResourceType gameResourceType;
        switch (format) {
            case "png":
                gameResourceType = Texture;
                break;
            case "mp3":
                gameResourceType = Music;
            case "ogg":
                gameResourceType = Sound;
            default:
                gameResourceType = None;
        }
        return gameResourceType.cls;
    }
}
