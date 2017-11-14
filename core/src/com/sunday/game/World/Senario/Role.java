package com.sunday.game.World.Senario;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

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
