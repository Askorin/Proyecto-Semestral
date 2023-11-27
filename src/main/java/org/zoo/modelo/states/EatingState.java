package org.zoo.modelo.states;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.animal.Animal;

public class EatingState implements State {
    private Animal animal;
    private final int EATING_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public EatingState(Animal animal) {
        this.animal = animal;
        FoodArea targetFoodArea = FoodArea.searchFoodContainer(animal.ownerHabitat);
        initMs = System.currentTimeMillis();
        currentMs = initMs;

        //Verificamos si hay comida
        if (targetFoodArea != null) {
            EnumFood foundFood = targetFoodArea.find(animal.getPrefferedFood());
            if (foundFood != null) {
                //Si hay comida seteamos el estado
                targetFoodArea.remove(foundFood);
                animal.restartHungerTimeElapsed();
                animal.setSprite(animal.getEatSprite());
                return;
            }
        }
        //Si no hay comida cambiamos de estado
        animal.changeState(this);
    }
    @Override
    public void stateBehavior() {
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= EATING_DURATION) {
            animal.restartHungerTimeElapsed();
            animal.changeState(this);
        }
    }
}
