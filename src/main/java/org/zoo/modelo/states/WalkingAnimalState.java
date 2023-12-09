package org.zoo.modelo.states;

import org.zoo.utilities.Utilities;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.modelo.animal.Animal;

//Este estado corresponde a caminar a un punto aleatorio
//es util para tener un comportamiente "por defecto" y no quedarse quieto
public class WalkingAnimalState implements AnimalState {
    private boolean hasBeenInitialized;
    private final Animal animal;
    private ZooPoint targetPoint; //punto aleatorio el cual es a donde se dirige el animal
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingAnimalState(Animal animal) {
        this.animal = animal;
    }
    @Override
    public void stateInit() {
        /* Revisamos si ya se inicializo el estado */
        if (hasBeenInitialized) {
            System.err.println("Se ha intentado inicializar un estado que ya ha sido inicializado.");
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
    public void stateUpdate() {
        if (!hasBeenInitialized) {
            System.err.println("Se ha detectado un estado.update() sin haber inicializado el estado.");
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
