package com.sunday.engine.physic;

import com.badlogic.gdx.physics.box2d.*;
import com.sunday.engine.common.data.CustomizedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhysicDefinition implements CustomizedData {
    private final List<FixtureDef> fixtureDefList = new ArrayList<>();
    private final Map<FixtureDef, Fixture> fixtureDefFixtureMap = new HashMap<>();
    public Object owner;
    private BodyDef bodyDef;

    public PhysicDefinition(Object owner) {
        this.owner = owner;
        bodyDef = new BodyDef();
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public void addFixtureDef(FixtureDef fixtureDef) {
        fixtureDefList.add(fixtureDef);
    }

    protected Body createBody(World world) {
        Body body = world.createBody(bodyDef);
        fixtureDefList.forEach(fixtureDef -> {
            fixtureDefFixtureMap.put(fixtureDef, body.createFixture(fixtureDef));
        });
        return body;
    }

}
