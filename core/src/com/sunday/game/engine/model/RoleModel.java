package com.sunday.game.engine.model;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.events.EventProcessor;
import com.sunday.game.engine.model.poperty.RoleLabel;
import com.sunday.game.engine.model.poperty.RoleProperty;
import com.sunday.game.engine.model.present.RolePresent;
import com.sunday.game.engine.model.reaction.RoleReaction;
import com.sunday.game.engine.view.View;

public abstract class RoleModel implements Disposable {
    protected View view;
    private RoleLabel roleLabel;
    protected boolean isModified = false;

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
