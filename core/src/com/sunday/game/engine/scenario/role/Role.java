package com.sunday.game.engine.scenario.role;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.Controller;
import com.sunday.game.engine.model.RoleModel;
import com.sunday.game.engine.view.View;

public class Role implements Disposable {
    public Controller controller;
    public RoleModel roleModel;
    public View view;

    public Role(Controller controller, RoleModel roleModel, View view) {
        this.controller = controller;
        this.roleModel = roleModel;
        this.view = view;
    }

    @Override
    public void dispose() {
        controller.dispose();
        roleModel.dispose();
        view.dispose();
    }
}
