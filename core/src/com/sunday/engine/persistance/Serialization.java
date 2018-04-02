package com.sunday.engine.persistance;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class Serialization<T> implements Json.Serializer<T> {

    @Override
    public void write(Json json, T object, Class knownType) {

    }

    @Override
    public T read(Json json, JsonValue jsonData, Class type) {
        return null;
    }
}
