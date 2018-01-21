package com.sunday.game.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.examples.enemy.EnemyModel;
import com.sunday.game.engine.examples.hero.HeroModel;
import com.sunday.game.engine.examples.map.MapModel;
import com.sunday.game.engine.model.AbstractModel;

public class RoleConstructor implements Disposable {

    public RoleConstructor() {
    }

    public Role construct(Label label) {
        AbstractModel abstractModel = null;
        switch (label) {
            case Hero:
                abstractModel = new HeroModel();
                break;
            case Enemy:
                abstractModel = new EnemyModel();
                break;
            case Map:
                abstractModel = new MapModel();
        }
        return new Role(label, abstractModel);
    }

    @Override
    public void dispose() {

    }
}
