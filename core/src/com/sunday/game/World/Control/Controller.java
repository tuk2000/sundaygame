package com.sunday.game.World.Control;

import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

public abstract class Controller {
    protected RoleModel roleModel;
    protected View view;
    protected EventProcessor eventProcessor;

    public void useRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
        this.eventProcessor = this.roleModel.getEventProcessor();
    }

    public void useView(View view) {
        this.view = view;
    }

    public void notifyGameEvent(GameEvent gameEvent) {
        eventProcessor.processEvent(gameEvent);
    }
}
