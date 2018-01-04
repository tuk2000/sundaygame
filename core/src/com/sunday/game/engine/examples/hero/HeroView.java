package com.sunday.game.engine.examples.hero;

import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.RoleAbstractView;

public class HeroView extends RoleAbstractView {

    private HeroAnimation heroAnimation;

    public HeroView() {
        heroAnimation = new HeroAnimation();
    }

    @Override
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        physicViewLayer.setViewComponent(abstractModel.physicReflection);
        screenViewLayer.setViewComponent(heroAnimation.getKeyFrame(abstractModel.movementState));
    }
}
