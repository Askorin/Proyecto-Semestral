package org.zoo.modelo.states;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.TextMessageManager;
import org.zoo.modelo.animal.Animal;

public class DeadAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    private final long initMs;
    private long currentMs;
    private final int ANIM_DURATION = Sprite.ANIMAL_DEAD_ANIM.getFramesNumber() * Sprite.ANIMAL_DEAD_ANIM.getTimePerFrame();
    public static final long  LIFETIME = 20000;

    public DeadAnimalState(Animal animal) {
        this.animal = animal;
        initMs = System.currentTimeMillis();
    }

    @Override
    public void stateInit() {
        if (hasBeenInitialized) {
            System.err.println("Se ha intentado inicializar un estado que ya ha sido inicializado.");
        }
        hasBeenInitialized = true;

        currentMs = initMs;
        animal.setSprite(Sprite.ANIMAL_DEAD_ANIM);
        animal.restartSpriteTimeElapsed();
    }

    @Override
    public void stateUpdate() {
        if (!hasBeenInitialized) {
            System.err.println("Se ha detectado un estado.update() sin haber inicializado el estado.");
        }

        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= ANIM_DURATION) {
            animal.setSprite(Sprite.ANIMAL_DEAD);
        }
        if (timeElapsed >= LIFETIME) {
            animal.ownerHabitat.getContainables().removeComponent(animal);
        }
    }

}
