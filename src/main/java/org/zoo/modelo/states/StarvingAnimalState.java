package org.zoo.modelo.states;

import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

public class StarvingAnimalState implements AnimalState {
    private final Animal animal;
    public StarvingAnimalState(Animal animal) {
        this.animal = animal;

        /* Hacemos una busqueda inicial */
        if (searchForFood()) {
            animal.changeStateV2(new GatheringAnimalState(animal));
            return;
        }
        animal.setSprite(animal.getHungrySprite());
    }
    @Override
    public void stateUpdate() {
        /* Este estado se termina si se encuentra comida */
        if (searchForFood()) {
            animal.changeStateV2(new GatheringAnimalState(animal));
            return;
        }

        /* Este estado se termina si se lleva mucho tiempo sin comer */
        if (animal.getHungerTimeElapsed() >= animal.getHungerMaxLimitMs()) {
            animal.changeStateV2(new DeadAnimalState(animal));
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
