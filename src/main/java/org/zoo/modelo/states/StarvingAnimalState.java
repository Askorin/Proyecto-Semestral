package org.zoo.modelo.states;

import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

public class StarvingAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    public StarvingAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() {
        if (hasBeenInitialized) {
            System.err.println("Se ha intentado inicializar un estado que ya ha sido inicializado.");
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
    public void stateUpdate() {
        if (!hasBeenInitialized) {
            System.err.println("Se ha detectado un estado.update() sin haber inicializado el estado.");
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
    private boolean searchForFood() {
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);

        if (targetFood != null) {
            return targetFood.find(animal.getPrefferedFood()) != null;
        }
        return false;
    }
}
