package com.sunday.game.engine.examples.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.view.MapAbstractView;

public class MapView extends MapAbstractView {

    public MapView() {
        super("TileMap/sTest/sTest.tmx");
        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-100, 0), new Vector2(900, 0)});

        fixtureDef.shape = chainShape;
        fixtureDef.friction = 2.0f;
        fixtureDef.restitution = 0;
        fixtureDef.density = 15f;

        PhysicDefinition physicDefinition = new PhysicDefinition(fixtureDef, bodyDef);
        physicViewLayer.setViewComponent(physicDefinition);
    }
}
