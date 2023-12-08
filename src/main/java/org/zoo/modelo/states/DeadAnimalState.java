package org.zoo.modelo.states;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.animal.Animal;

public class DeadAnimalState implements AnimalState {
    private final Animal animal;
    private final int ANIM_DURATION = Sprite.ANIMAL_DEAD_ANIM.getFramesNumber() * Sprite.ANIMAL_DEAD_ANIM.getTimePerFrame();
    private final long initMs;
    private long currentMs;

    public DeadAnimalState(Animal animal) {
        this.animal = animal;
        initMs = System.currentTimeMillis();
        currentMs = initMs;
        animal.setSprite(Sprite.ANIMAL_DEAD_ANIM);
        animal.restartSpriteTimeElapsed();
    }

    @Override
    public void stateUpdate() {
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= ANIM_DURATION) {
            animal.setSprite(Sprite.ANIMAL_DEAD);
        }
    }

}
