package org.zoo.modelo.states;

import org.zoo.modelo.Zoo;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Utilities;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;

public class GatheringAnimalState implements AnimalState {
    private final Animal animal;
    private ZooPoint targetPoint;
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public GatheringAnimalState(Animal animal) {
        this.animal = animal;

        /* Buscamos el punto con comida a donde dirigirse */
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);
        if (targetFood == null) {
            targetPoint = new ZooPoint(animal.x, animal.y);
        }
        else {
            Hitbox animalHitbox = new Hitbox(animal.x, animal.y, animal.getWidth(), animal.getHeight());
            Hitbox foodHitbox = targetFood.getHitbox();
            targetPoint = Hitbox.getCloserPointToHitbox(animalHitbox, foodHitbox);
        }
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateUpdate() {
        /* El animal se dirige a el target */
        ZooPoint direction = ZooPoint.getDifference(targetPoint, new ZooPoint(animal.x, animal.y));
        ZooPoint velocity = Utilities.getNormalizedVector(direction, speed);

        if (animal.x < targetPoint.x) {
            animal.x += velocity.x;
            if (animal.x > targetPoint.x) animal.x = targetPoint.x;
        }
        else if (animal.x > targetPoint.x) {
            animal.x += velocity.x;
            if (animal.x < targetPoint.x) animal.x = targetPoint.x;
        }

        if (animal.y < targetPoint.y) {
            animal.y += velocity.y;
            if (animal.y > targetPoint.y) animal.y = targetPoint.y;
        }
        else if (animal.y > targetPoint.y) {
            animal.y += velocity.y;
            if (animal.y < targetPoint.y) animal.y = targetPoint.y;
        }

        /* Si el animal llega al target, salimos del estado */
        if (animal.x == targetPoint.x && animal.y == targetPoint.y) {
            /* A cual estado cambiamos depende de si hay comida */
            if (searchForFood()) {
                animal.changeStateV2(new EatingAnimalState(animal));
                return;
            }
            animal.changeStateV2(new StarvingAnimalState(animal));
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
