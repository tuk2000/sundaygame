package com.sunday.engine.model.property;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.physic.CollisionContext;

@DataMark(type = DataType.Customized, signalClass = {DataSignal.class, PhysicReflectionSignal.class}, contextClass = CollisionContext.class)
public class PhysicDefinition implements CustomizedData {
    public final FixtureDef fixtureDef = new FixtureDef();
    public final BodyDef bodyDef = new BodyDef();
    public final PhysicBody physicBody = new PhysicBody(this);
    public Object owner;
    private Shape dummyShape = new CircleShape();

    public PhysicDefinition(Object owner) {
        this.owner = owner;
        fixtureDef.shape = dummyShape;
    }
}
