package com.sunday.game.engine.scenario.role;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.Controller;
import com.sunday.game.engine.control.EnemyController;
import com.sunday.game.engine.control.HeroController;
import com.sunday.game.engine.control.MapController;
import com.sunday.game.engine.model.DefaultHero;
import com.sunday.game.engine.model.Enemy;
import com.sunday.game.engine.model.MapModel;
import com.sunday.game.engine.model.RoleModel;
import com.sunday.game.engine.model.poperty.RoleLabel;
import com.sunday.game.engine.scenario.GameScenarioEngine;
import com.sunday.game.engine.view.View;
import com.sunday.game.engine.view.views.EnemyView;
import com.sunday.game.engine.view.views.HeroView;
import com.sunday.game.engine.view.views.MapView;

public class RoleConstructor implements Disposable {
    private GameScenarioEngine gameScenarioEngine;

    public RoleConstructor(GameScenarioEngine gameScenarioEngine) {
        this.gameScenarioEngine = gameScenarioEngine;
    }

    public Role construct(RoleLabel roleLabel) {
        Controller controller = null;
        RoleModel roleModel = null;
        View view = null;
        switch (roleLabel) {
            case Hero:
                controller = new HeroController();
                roleModel = new DefaultHero();
                view = new HeroView();
                break;
            case Enemy:
                controller = new EnemyController();
                roleModel = new Enemy();
                view = new EnemyView();
                break;
            case Map:
                controller = new MapController();
                roleModel = new MapModel();
                view = new MapView();
        }
        if (controller != null & roleModel != null & view != null) {
            controller.useRoleModel(roleModel);
            controller.useView(view);
            roleModel.useView(view);
        }
        return new Role(controller, roleModel, view);
    }

    @Override
    public void dispose() {

    }
}
