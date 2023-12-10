package org.zoo.modelo.states;

import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NoInitializedStateException;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta quieto.
 * Basicamente, este estado es pasar una cantidad aleatoria de tiempo sin hacer nada.
 */
public class IdleAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    /**
     * La cantidad aleatoria de tiempo que el <code>Animal</code> se queda en este estado, sin moverse.
     */
    private final int IDLE_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public IdleAnimalState(Animal animal) {
        this.animal = animal;
        initMs = System.currentTimeMillis();
    }
    @Override
    public void stateInit() throws AlreadyInitializedStateException {
        /* Revisamos si ya se inicializo el estado */
        if (hasBeenInitialized) {
            throw new AlreadyInitializedStateException();
        }
        hasBeenInitialized = true;

        currentMs = initMs;
        animal.setSprite(animal.getIdleSprite());
    }

    @Override
    public void stateUpdate() throws NoInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NoInitializedStateException();
        }
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
