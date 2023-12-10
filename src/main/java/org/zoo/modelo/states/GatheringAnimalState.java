package org.zoo.modelo.states;

import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NotInitializedStateException;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Utilities;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta buscando comida.
 * Basicamente, en este estado el animal ya localizo la comida y se dirige a ella.
 */
public class GatheringAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    /**
     * Corresponde al punto (<code>FoodArea</code>) donde el animal detecto comida y se dirige
     */
    private ZooPoint targetPoint;
    /**
     * La velocidad con que se mueve es aleatoria y constante para la duracion del estado
     */
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public GatheringAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() throws AlreadyInitializedStateException {
        /* Revisamos si ya se inicializo el estado */
        if (hasBeenInitialized) {
            throw new AlreadyInitializedStateException();
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
            targetPoint = Hitbox.getClosestPointToHitbox(animalHitbox, foodHitbox);
        }


        /* Cambiamos el Sprite */
        animal.setSprite(animal.getWalkSprite());

        if (targetPoint.x < animal.x) animal.setFlipped(true); // Damos vuelta el Sprite en caso de necesario
        else if (targetPoint.x > animal.x) animal.setFlipped(false);
    }

    @Override
    public void stateUpdate() throws NotInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NotInitializedStateException();
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
