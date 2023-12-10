package org.zoo.modelo.states;

import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NotInitializedStateException;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta comiendo.
 * Basicamente, este estado es la animación al comer
 */
public class EatingAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    /**
     * La duración de este estado es un numero aleatorio
     */
    private final int EATING_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public EatingAnimalState(Animal animal) {
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
        FoodArea targetFoodArea = FoodArea.searchFoodContainer(animal.ownerHabitat);

        /* Verificamos si hay comida */
        if (targetFoodArea != null) {
            EnumFood foundFood = targetFoodArea.find(animal.getPrefferedFood());
            if (foundFood != null) {

                /* Si hay comida, la eliminamos del contenedor y seteamos el estado */
                targetFoodArea.remove(foundFood);
                animal.restartHungerTimeElapsed();
                animal.setSprite(animal.getEatSprite());
                return;
            }
        }
        /* Si no hay comida cambiamos de estado */
        animal.changeState(new StarvingAnimalState(animal));
        return;
    }

    @Override
    public void stateUpdate() throws NotInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NotInitializedStateException();
        }
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= EATING_DURATION) {
            animal.restartHungerTimeElapsed();
            animal.changeState(new WalkingAnimalState(animal));
            return;
        }
    }
}
