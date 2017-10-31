package com.sunday.game.World.Model;

import com.sunday.game.World.Control.EventProcessor;
import com.sunday.game.World.Model.Present.RolePresent;
import com.sunday.game.World.Model.Property.RoleProperty;
import com.sunday.game.World.Model.Reaction.RoleReaction;
import com.sunday.game.World.View.View;

public abstract class RoleModel {
    protected View view;

    public abstract EventProcessor getEventProcessor();

    public void useView(View view) {
        this.view = view;
    }

    protected RoleProperty property;
    protected RolePresent present;
    protected RoleReaction reaction;
}
