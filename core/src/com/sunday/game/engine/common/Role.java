package com.sunday.game.engine.common;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.AbstractView;

public class Role implements Disposable {
    public RoleLabel roleLabel;
    public AbstractController abstractController;
    public AbstractModel abstractModel;
    public AbstractView abstractView;

    public Role(RoleLabel roleLabel, AbstractController abstractController, AbstractModel abstractModel, AbstractView abstractView) {
        this.roleLabel = roleLabel;
        this.abstractController = abstractController;
        this.abstractModel = abstractModel;
        this.abstractView = abstractView;
    }

    @Override
    public void dispose() {
        abstractController.dispose();
        abstractModel.dispose();
        abstractView.dispose();
    }

    public void synchronize() {
        abstractView.synchronizeWithRoleModel(abstractModel);
    }
}
