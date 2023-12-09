package org.zoo.modelo.food;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.Sprite;

public enum EnumFood implements MenuItem {
    FISH(Sprite.FISH_INGAME),
    LEAVES(Sprite.LEAVES_INGAME),
    MEAT(Sprite.MEAT_INGAME),
    BANANA(Sprite.BANANA_INGAME);
    private final Sprite inGameSprite;
    EnumFood(Sprite inGameSprite) {
        this.inGameSprite = inGameSprite;
    }
    @Override
    public Sprite getInGameSprite() {
        return inGameSprite;
    }

}
