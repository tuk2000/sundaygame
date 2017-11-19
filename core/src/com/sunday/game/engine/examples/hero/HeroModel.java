package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;

    public HeroModel() {
        heroEventProcessor = new HeroEventProcessor(this);
        Outlook outlook = new Outlook(Shape.Type.Polygon, new Vector2(64.0f, 64.0f));
        setOutlook(outlook);
        movementState.position.set(32,32);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return heroEventProcessor;
    }

    @Override
    public void dispose() {

    }
}
