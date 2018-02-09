package com.sunday.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.examples.enemy.EnemyModel;
import com.sunday.engine.examples.hero.HeroModel;
import com.sunday.engine.examples.map.MapModel;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.state.Label;

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
