package com.sunday.game.engine.examples;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.common.enums.Label;
import com.sunday.game.engine.model.AbstractModel;

public class Role implements Disposable {
    public Label label;
    public AbstractModel abstractModel;

    public Role(Label label, AbstractModel abstractModel) {
        this.label = label;
        this.abstractModel = abstractModel;
    }

    @Override
    public void dispose() {
        abstractModel.dispose();
    }
}
