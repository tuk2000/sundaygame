package com.sunday.game.World.View.Views;

import com.sunday.game.World.View.Sprites.HeroSprite;
import com.sunday.game.World.View.ViewType;

public class HeroView extends RoleView {
    private HeroSprite heroSprite;

    public HeroView() {
        super(ViewType.Sprite);
        heroSprite = new HeroSprite();
    }
}
