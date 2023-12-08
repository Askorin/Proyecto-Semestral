package org.zoo.modelo.states;

import org.zoo.modelo.animal.Animal;

public class IdleAnimalState implements AnimalState {
    private final Animal animal;
    private final int IDLE_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public IdleAnimalState(Animal animal) {
        this.animal = animal;
        initMs = System.currentTimeMillis();
        currentMs = initMs;
        animal.setSprite(animal.getIdleSprite());
    }
    @Override
    public void stateUpdate() {

        /* Revisamos si el animal tiene hambre */
        if (animal.getHungerTimeElapsed() >= animal.getHungerLimitMs()) {
            animal.changeState(new StarvingAnimalState(animal));
            return;
        }

        /* Revisamos si lleva mucho tiempo detenido */
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= IDLE_DURATION) {
            animal.changeState(new WalkingAnimalState(animal));
            return;
        }
    }
}
