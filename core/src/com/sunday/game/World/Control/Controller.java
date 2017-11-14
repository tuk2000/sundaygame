package com.sunday.game.World.Control;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.World.Model.RoleModel;
import com.sunday.game.World.View.View;

public abstract class Controller implements Disposable {
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
