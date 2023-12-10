package org.zoo.modelo.states;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NoInitializedStateException;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta muerto.
 * Basicamente este estado reproduce un animación de muerte, y luego
 * permanece un tiempo hasta que el <code>Animal</code> se borra
 */
public class DeadAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    private final long initMs;
    private long currentMs;
    /**
     * Inicialmente pasamos por una animación, corresponde a la duración de esta
     */
    private final int ANIM_DURATION = Sprite.ANIMAL_DEAD_ANIM.getFramesNumber() * Sprite.ANIMAL_DEAD_ANIM.getTimePerFrame();
    /**
     * El tiempo que permanece en este estado el <code>Animal</code> antes de ser borrado
     */
    public static final long  LIFETIME = 20000;

    public DeadAnimalState(Animal animal) {
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
        animal.setSprite(Sprite.ANIMAL_DEAD_ANIM);
        animal.restartSpriteTimeElapsed();
    }

    @Override
    public void stateUpdate() throws NoInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NoInitializedStateException();
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
