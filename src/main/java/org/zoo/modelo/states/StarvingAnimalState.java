package org.zoo.modelo.states;

import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NoInitializedStateException;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta hambriento, pidiendo comida.
 * Basicamente, este estado es el animal, quieto, informando que tiene hambre.
 */
public class StarvingAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    public StarvingAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() throws AlreadyInitializedStateException {
        /* Revisamos si ya se inicializo el estado */
        if (hasBeenInitialized) {
            throw new AlreadyInitializedStateException();
        }
        hasBeenInitialized = true;

        /* Hacemos una busqueda inicial */
        if (searchForFood()) {
            animal.changeState(new GatheringAnimalState(animal));
            return;
        }
        animal.setSprite(animal.getHungrySprite());
    }

    @Override
    public void stateUpdate() throws NoInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NoInitializedStateException();
        }
        /* Este estado se termina si se encuentra comida */
        if (searchForFood()) {
            animal.changeState(new GatheringAnimalState(animal));
            return;
        }

        /* Este estado se termina si se lleva mucho tiempo sin comer */
        if (animal.getHungerTimeElapsed() >= animal.getHungerMaxLimitMs()) {
            animal.changeState(new DeadAnimalState(animal));
            return;
        }
    }
    /**
     * Revisa si hay comida en el habitat
     * @return <code>true</code> si encuentra, <code>false</code> en caso contrario
     */
    private boolean searchForFood() {
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);

        if (targetFood != null) {
            return targetFood.find(animal.getPrefferedFood()) != null;
        }
        return false;
    }
}
