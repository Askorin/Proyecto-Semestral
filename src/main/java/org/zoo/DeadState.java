package org.zoo;

public class DeadState implements State {
    private Animal animal;
    public DeadState(Animal animal) {
        this.animal = animal;
        animal.setSprite(Sprite.FISH_INGAME);
    }
    @Override
    public void stateBehavior() {

    }
}
