package com.sunday.game.engine.examples.enemy;

import com.badlogic.gdx.math.Vector2;
import com.sunday.game.engine.common.RolePresent;
import com.sunday.game.engine.common.RoleShape;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.model.AbstractModel;

public class EnemyModel extends AbstractModel {
    public EnemyModel() {
        RolePresent rolePresent = new RolePresent(RoleShape.Circle, new Vector2(40.0f, 40.0f));
        setRolePresent(rolePresent);
        roleMovementStatus.position.set(112,32);
    }

    @Override
    public EventProcessor getEventProcessor() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
