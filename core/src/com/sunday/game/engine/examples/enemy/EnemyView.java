package com.sunday.game.engine.examples.enemy;

import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.RoleAbstractView;

public class EnemyView extends RoleAbstractView {
    private SawAnimation sawAnimation;

    public EnemyView() {
        sawAnimation = new SawAnimation();
    }

    @Override
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        physicViewLayer.setViewComponent(abstractModel.physicReflection);
        screenViewLayer.setViewComponent(sawAnimation.getKeyFrame());
    }
}
