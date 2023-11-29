package org.zoo.modelo.states;

import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;

public class GatheringState implements State {
    private final Animal animal;
    private final int targetX;
    private final int targetY;
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public GatheringState(Animal animal) {
        this.animal = animal;
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);
        if (targetFood == null) {
            targetX = animal.x;
            targetY = animal.y;
        }
        else {
            Hitbox animalHitbox = new Hitbox(animal.x, animal.y, animal.getWidth(), animal.getHeight());
            Hitbox foodHitbox = targetFood.getHitbox();
            ZooPoint targetZooPoint = Hitbox.getCloserPointToHitbox(animalHitbox, foodHitbox);
            targetX = targetZooPoint.x;
            targetY = targetZooPoint.y;
        }
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateBehavior() {
        //el animal se mueve hasta que coincida con el target
        if (animal.x < targetX) {
            animal.x += getSpeedX(animal);
            if (animal.x > targetX) animal.x = targetX;
        }
        else if (animal.x > targetX) {
            animal.x += getSpeedX(animal);
            if (animal.x < targetX) animal.x = targetX;
        }

        if (animal.y < targetY) {
            animal.y += getSpeedY(animal);
            if (animal.y > targetY) animal.y = targetY;
        }
        else if (animal.y > targetY) {
            animal.y += getSpeedY(animal);
            if (animal.y < targetY) animal.y = targetY;
        }

        if (animal.x == targetX && animal.y == targetY) {
            animal.changeState(this);
        }
    }
    //método para devolver la componente horizontal de la velocidad con norma = speed
    private int getSpeedX(Animal animal) {
        double speedXnotNorm = targetX - animal.x;
        double speedYnotNorm = targetY - animal.y;
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                +  speedYnotNorm * speedYnotNorm);
        double speedX = (speed * speedXnotNorm) / norm;
        return  (int)speedX;
    }
    //método para devolver la componente vertical de la velocidad con norma = speed
    private int getSpeedY(Animal animal) {
        double speedXnotNorm = targetX - animal.x;
        double speedYnotNorm = targetY - animal.y;
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                +  speedYnotNorm * speedYnotNorm);
        double speedY = (speed * speedYnotNorm) / norm;
        return  (int)speedY;
    }
}
