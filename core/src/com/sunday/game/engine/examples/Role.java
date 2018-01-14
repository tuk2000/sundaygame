package com.sunday.game.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.control.AbstractController;
import com.sunday.game.engine.model.AbstractModel;

public class Role implements Disposable {
    public Label label;
    public AbstractController abstractController;
    public AbstractModel abstractModel;

    public Role(Label label, AbstractController abstractController, AbstractModel abstractModel) {
        this.label = label;
        this.abstractController = abstractController;
        this.abstractModel = abstractModel;
    }

    @Override
    public void dispose() {
        abstractController.dispose();
        abstractModel.dispose();
    }
}
