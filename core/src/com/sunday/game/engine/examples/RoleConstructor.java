package com.sunday.game.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.examples.enemy.EnemyController;
import com.sunday.game.engine.examples.enemy.EnemyModel;
import com.sunday.game.engine.examples.enemy.EnemyView;
import com.sunday.game.engine.examples.hero.HeroController;
import com.sunday.game.engine.examples.hero.HeroModel;
import com.sunday.game.engine.examples.hero.HeroView;
import com.sunday.game.engine.examples.map.MapController;
import com.sunday.game.engine.examples.map.MapModel;
import com.sunday.game.engine.examples.map.MapView;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.AbstractView;

public class RoleConstructor implements Disposable {

    public RoleConstructor() {
    }

    public Role construct(Label label) {
        AbstractController abstractController = null;
        AbstractModel abstractModel = null;
        AbstractView abstractView = null;
        switch (label) {
            case Hero:
                abstractController = new HeroController();
                abstractModel = new HeroModel();
                abstractView = new HeroView();
                break;
            case Enemy:
                abstractController = new EnemyController();
                abstractModel = new EnemyModel();
                abstractView = new EnemyView();
                break;
            case Map:
                abstractController = new MapController();
                abstractModel = new MapModel();
                abstractView = new MapView();
        }
        if (abstractController != null & abstractModel != null & abstractView != null) {
            abstractController.useRoleModel(abstractModel);
            abstractController.useView(abstractView);
            abstractView.synchronizeWithRoleModel(abstractModel);
        }
        return new Role(label, abstractController, abstractModel, abstractView);
    }

    @Override
    public void dispose() {

    }
}
