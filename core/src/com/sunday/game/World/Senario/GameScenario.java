package com.sunday.game.World.Senario;

import com.sunday.game.World.Control.Controller;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

import java.awt.*;
import java.util.ArrayList;

public class GameScenario {
    public static GameScenario Root;
    private GameScenario parent;
    private GameScenarioScope scenarioScope;
    private ArrayList<GameScenario> kids = new ArrayList<>();

    private EventQueue eventQueue = new EventQueue();
    private ArrayList<Controller> controllers = new ArrayList<>();
    private ArrayList<RoleModel> roleModels = new ArrayList<>();
    private ArrayList<View> views = new ArrayList<>();
    private ArrayList<MVCGroup> groups = new ArrayList<>();

    public GameScenario(boolean isRoot, GameScenarioScope scenarioScope) {
        if (isRoot) {
            Root = this;
            parent = null;
        }
        this.scenarioScope = scenarioScope;
    }

    public boolean isRoot() {
        return this.equals(Root);
    }


    public void renderViews(){
        views.forEach(e->{

        });
    }

    public void addMVCGroup(MVCGroup mvcGroup) {
        controllers.add(mvcGroup.controller);
        roleModels.add(mvcGroup.roleModel);
        views.add(mvcGroup.view);
        groups.add(mvcGroup);
        mvcGroup.bind();
    }
}
