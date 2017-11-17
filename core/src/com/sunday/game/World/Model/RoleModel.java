package com.sunday.game.World.Model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Control.EventProcessor;
import com.sunday.game.World.Model.Present.RolePresent;
import com.sunday.game.World.Model.Property.RoleLabel;
import com.sunday.game.World.Model.Property.RoleProperty;
import com.sunday.game.World.Model.Reaction.RoleReaction;
import com.sunday.game.World.View.View;

public abstract class RoleModel implements Disposable {
    protected View view;
    private RoleLabel roleLabel;
    protected boolean isModified=false;

    public boolean isModified() {
        return isModified;
    }

    public RoleModel(RoleLabel roleLabel) {
        this.roleLabel = roleLabel;
    }

    public RoleLabel getRoleLabel() {
        return roleLabel;
    }

    public abstract EventProcessor getEventProcessor();

    public void useView(View view) {
        this.view = view;
    }

    protected RoleProperty property;
    protected RolePresent present;
    protected RoleReaction reaction;


}
