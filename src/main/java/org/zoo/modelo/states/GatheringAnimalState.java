package org.zoo.modelo.states;

import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Utilities;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;

public class GatheringAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    private ZooPoint targetPoint;
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public GatheringAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() {
        if (hasBeenInitialized) {
            System.err.println("Se ha intentado inicializar un estado que ya ha sido inicializado.");
        }
        hasBeenInitialized = true;

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


        /* Cambiamos el Sprite */
        animal.setSprite(animal.getWalkSprite());

        if (targetPoint.x < animal.x) animal.setFlipped(true); // Damos vuelta el Sprite en caso de necesario
        else if (targetPoint.x > animal.x) animal.setFlipped(false);
    }

    @Override
    public void stateUpdate() {
        if (!hasBeenInitialized) {
            System.err.println("Se ha detectado un estado.update() sin haber inicializado el estado.");
        }
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
                animal.changeState(new EatingAnimalState(animal));
                return;
            }
            animal.changeState(new StarvingAnimalState(animal));
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
