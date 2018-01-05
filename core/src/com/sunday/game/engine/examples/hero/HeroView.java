package com.sunday.game.engine.examples.hero;

import com.sunday.game.engine.view.RoleAbstractView;

public class HeroView extends RoleAbstractView {

    private HeroAnimation heroAnimation;

    public HeroView() {
        heroAnimation = new HeroAnimation();
    }
}
