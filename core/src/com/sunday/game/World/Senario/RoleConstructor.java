package com.sunday.game.World.Senario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Control.Controllers.EnemyController;
import com.sunday.game.World.Control.Controllers.HeroController;
import com.sunday.game.World.Control.Controllers.MapController;
import com.sunday.game.World.Model.DefaultHero;
import com.sunday.game.World.Model.Enemy;
import com.sunday.game.World.Model.MapModel;
import com.sunday.game.World.Model.Property.RoleLabel;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;
import com.sunday.game.World.View.Views.EnemyView;
import com.sunday.game.World.View.Views.HeroView;
import com.sunday.game.World.View.Views.MapView;

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
