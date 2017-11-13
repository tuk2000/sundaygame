package com.sunday.game.World.Senario;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

public class MVCGroup {
    public Controller controller;
    public RoleModel roleModel;
    public View view;

    public MVCGroup(Controller controller, RoleModel roleModel, View view) {
        this.controller = controller;
        this.roleModel = roleModel;
        this.view = view;
    }

    void bind() {
        controller.useRoleModel(roleModel);
        controller.useView(view);
        roleModel.useView(view);
    }
}
