package org.zoo.modelo.food;

import org.zoo.modelo.Sprite;

public enum EnumFood {
    FISH(Sprite.FISH_INGAME),
    LEAVES(Sprite.LEAVES_INGAME);
    private final Sprite inGameSprite;
    EnumFood(Sprite inGameSprite) {
        this.inGameSprite = inGameSprite;
    }
    public Sprite getInGameSprite() {
        return inGameSprite;
    }
}
