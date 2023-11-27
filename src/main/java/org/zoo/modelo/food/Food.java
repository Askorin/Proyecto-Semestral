package org.zoo.modelo.food;

import org.zoo.modelo.Sprite;

public enum Food {
    FISH(Sprite.FISH_INGAME),
    LEAVES(Sprite.LEAVES_INGAME);
    private Sprite inGameSprite;
    private Food(Sprite inGameSprite) {
        this.inGameSprite = inGameSprite;
    }
    public Sprite getInGameSprite() {
        return inGameSprite;
    }
}
