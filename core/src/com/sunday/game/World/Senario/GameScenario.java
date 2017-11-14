package com.sunday.game.World.Senario;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class GameScenario implements Disposable {
    private GameScenarioScope scenarioScope;
    private GameScenario parent;
    private ArrayList<GameScenario> kids = new ArrayList<>();
    private ArrayList<Role> roles = new ArrayList<>();

    public GameScenario(GameScenarioScope scenarioScope) {
        this.scenarioScope = scenarioScope;
    }

    public void addKid(GameScenario gameScenario) {
        if (!kids.contains(gameScenario)) {
            kids.add(gameScenario);
            gameScenario.parent = this;
        }
    }

    public void removeKid(GameScenario gameScenario) {
        if (kids.contains(gameScenario))
            kids.remove(gameScenario);
    }

    public ArrayList<GameScenario> getKids() {
        return kids;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    @Override
    public void dispose() {
        scenarioScope=null;
        parent=null;
        kids.forEach(e->e.dispose());
        kids.clear();
        roles.forEach(e ->e.dispose() );
        roles.clear();
    }
}
