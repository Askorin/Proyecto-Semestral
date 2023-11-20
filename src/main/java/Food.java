public enum Food {
    FISH(Sprite.FISH_INGAME),
    HAY(Sprite.CAT_WALK);
    private Sprite inGameSprite;
    private Food(Sprite inGameSprite) {
        this.inGameSprite = inGameSprite;
    }
    public Sprite getInGameSprite() {
        return inGameSprite;
    }
}
