package com.sunday.game.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.examples.enemy.EnemyController;
import com.sunday.game.engine.examples.enemy.EnemyModel;
import com.sunday.game.engine.examples.hero.HeroController;
import com.sunday.game.engine.examples.hero.HeroModel;
import com.sunday.game.engine.examples.map.MapController;
import com.sunday.game.engine.examples.map.MapModel;
import com.sunday.game.engine.model.AbstractModel;

public class RoleConstructor implements Disposable {

    public RoleConstructor() {
    }

    public Role construct(Label label) {
        AbstractController abstractController = null;
        AbstractModel abstractModel = null;
        switch (label) {
            case Hero:
                abstractController = new HeroController();
                abstractModel = new HeroModel();
                break;
            case Enemy:
                abstractController = new EnemyController();
                abstractModel = new EnemyModel();
                break;
            case Map:
                abstractController = new MapController();
                abstractModel = new MapModel();
        }
        if (abstractController != null & abstractModel != null ) {
            abstractController.useRoleModel(abstractModel);
        }
        return new Role(label, abstractController, abstractModel);
    }

    @Override
    public void dispose() {

    }
}
