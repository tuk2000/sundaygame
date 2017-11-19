package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.sunday.game.engine.common.RolePresent;
import com.sunday.game.engine.common.RoleShape;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;

    public HeroModel() {
        heroEventProcessor = new HeroEventProcessor(this);
        RolePresent rolePresent = new RolePresent(RoleShape.Rectangle, new Vector2(64.0f, 64.0f));
        setRolePresent(rolePresent);
        roleMovementStatus.position.set(32,32);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return heroEventProcessor;
    }

    @Override
    public void dispose() {

    }
}
