package com.sunday.game.World.Senario;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameScenario {
    private EventQueue eventQueue = new EventQueue();
    private ArrayList<Controller> controllers = new ArrayList<>();
    private ArrayList<RoleModel> roleModels = new ArrayList<>();
    private ArrayList<View> views = new ArrayList<>();
    private ArrayList<MVCGroup> groups = new ArrayList<>();

    public GameScenario() {

    }

    public void addComponent(Object object) {
        if (object instanceof Controller) {
            if (!controllers.contains(object)) {
                controllers.add((Controller) object);
            }
        } else if (object instanceof RoleModel) {
            if (!roleModels.contains(object)) {
                roleModels.add((RoleModel) object);
            }
        } else if (object instanceof View) {
            if (!views.contains(object)) {
                views.add((View) object);
            }
        }
    }

    public void bind(Controller controller, RoleModel roleModel, View view) {
        if (!controllers.contains(controller) || !roleModels.contains(roleModel) || !views.contains(view)) {
            controllers.add(controller);
            roleModels.add(roleModel);
            views.add(view);
            MVCGroup group = new MVCGroup(controller, roleModel, view);
            groups.add(group);
            group.bind();
        }
    }
}
