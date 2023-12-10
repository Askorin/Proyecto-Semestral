package org.zoo.modelo.states;

import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NoInitializedStateException;
import org.zoo.utilities.Utilities;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.modelo.animal.Animal;

/**
 * Estado que describe el comportamiento de un <code>Animal</code>
 * cuando este esta caminando como accion por defecto.
 * Basicamente, en este estado el animal toma un punto aleatorio y se deplaza a el.
 */
public class WalkingAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    /**
     * Punto aleatorio el cual es a donde se dirige <code>Animal</code>
     */
    private ZooPoint targetPoint;
    /**
     * La velocidad con que se mueve es aleatoria y constante para la duracion del estado
     */
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() throws AlreadyInitializedStateException {
        /* Revisamos si ya se inicializo el estado */
        if (hasBeenInitialized) {
            throw new AlreadyInitializedStateException();
        }
        hasBeenInitialized = true;

        /* Definimos el punto a donde va a moverse el animal */
        int targetX;
        int targetY;
        while (true) {
            //es importante que el target no esta fuera de los limites del habitat
            targetX = (int) (Math.random() * (animal.ownerHabitat.getWidth() - animal.getWidth()));
            targetY = (int) (Math.random()*(animal.ownerHabitat.getHeight() - animal.getHeight()));

            //es importante que exista un camino al target donde pueda ir el animal
            //TODO: IMPORTANTE TENER EN CUENTA QUE:
            /*Actualmente el State no calcula si el camino a seguir esta despejado,
              solo se fija si el punto final esta despejado (despejado = sin colisiones)
             */
            boolean collisionFound = false;
            Hitbox hitbox1 = new Hitbox(targetX, targetY, animal.getWidth(), animal.getHeight());
            for (Unblockable u: animal.ownerHabitat.getContainables().getUnblockables()) {
                Hitbox hitbox2 = u.getHitbox();
                if ( Hitbox.checkHitboxCollision(hitbox1, hitbox2) ) {
                    collisionFound = true;
                }
            }
            if ( ! collisionFound ) {break;}
        }

        targetPoint = new ZooPoint(targetX, targetY);

        /* Cambiamos el Sprite */
        animal.setSprite(animal.getWalkSprite());

        if (targetPoint.x < animal.x) animal.setFlipped(true); // Damos vuelta el Sprite en caso de necesario
        else if (targetPoint.x > animal.x) animal.setFlipped(false);
    }

    @Override
    public void stateUpdate() throws NoInitializedStateException {
        /* Revisamos si el estado se ha inicializado */
        if (!hasBeenInitialized) {
            throw new NoInitializedStateException();
        }
        /* Revisamos si el animal tiene hambre */
        if (animal.getHungerTimeElapsed() >= animal.getHungerLimitMs()) {
            animal.changeState(new StarvingAnimalState(animal));
            return;
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
            animal.changeState(new IdleAnimalState(animal));
            return;
        }
    }
}
