package org.zoo.modelo.states;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.animal.Animal;

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
