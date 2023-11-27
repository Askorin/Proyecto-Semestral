package org.zoo.modelo.states;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

public class StarvingState implements State {
    private Animal animal;
    public StarvingState(Animal animal) {
        this.animal = animal;
        //Hacemos una busqueda inicial
        if (searchForFood(animal)) {
            animal.changeState(this);
            return;
        }
        animal.setSprite(animal.getHungrySprite());
    }
    @Override
    public void stateBehavior() {
        //Este estado se mantiene hasta que encuentra comida
        if (searchForFood(animal)) {
            animal.changeState(this);
            return;
        }

        //A menos que haya pasado mucho tiempo sin comer
        if (animal.getHungerTimeElapsed() >= animal.getHungerMaxLimitMs()) {
            animal.changeState(this);
            return;
        }
    }
    private boolean searchForFood(Animal animal) {
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);

        if (targetFood != null) {
            if (targetFood.find(animal.getPrefferedFood()) != null) {
                return true;
            }
        }
        return false;
    }
}
