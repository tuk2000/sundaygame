package com.sunday.game.World.Senario;

import java.util.ArrayList;

public class GameScenario {
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

    public void addRole(Role role) {
        roles.add(role);
    }
}
