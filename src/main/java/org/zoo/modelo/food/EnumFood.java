package org.zoo.modelo.food;

import org.zoo.modelo.Sprite;

public enum EnumFood {
    FISH(Sprite.FISH_INGAME),
    LEAVES(Sprite.LEAVES_INGAME);
    private Sprite inGameSprite;
    private EnumFood(Sprite inGameSprite) {
        this.inGameSprite = inGameSprite;
    }
    public Sprite getInGameSprite() {
        return inGameSprite;
    }
}
