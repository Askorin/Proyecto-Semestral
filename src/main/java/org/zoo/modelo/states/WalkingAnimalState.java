package org.zoo.modelo.states;

import org.zoo.utilities.Utilities;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.characteristics.Unblockable;
import org.zoo.modelo.animal.Animal;

//Este estado corresponde a caminar a un punto aleatorio
//es util para tener un comportamiente "por defecto" y no quedarse quieto
public class WalkingAnimalState implements AnimalState {
    private final Animal animal;
    private final ZooPoint target; //punto aleatorio el cual es a donde se dirige el animal
    private final int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingAnimalState(Animal animal) {
        this.animal = animal;
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
        target = new ZooPoint(targetX, targetY);
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateUpdate() {

        /* Revisamos si el animal tiene hambre */
        if (animal.getHungerTimeElapsed() >= animal.getHungerLimitMs()) {
            animal.changeStateV2(new StarvingAnimalState(animal));
            return;
        }

        /* El animal se dirige a el target */
        ZooPoint direction = ZooPoint.getDifference(target, new ZooPoint(animal.x, animal.y));
        ZooPoint velocity = Utilities.getNormalizedVector(direction, speed);

        if (animal.x < target.x) {
            animal.x += velocity.x;
            if (animal.x > target.x) animal.x = target.x;
        }
        else if (animal.x > target.x) {
            animal.x += velocity.x;
            if (animal.x < target.x) animal.x = target.x;
        }

        if (animal.y < target.y) {
            animal.y += velocity.y;
            if (animal.y > target.y) animal.y = target.y;
        }
        else if (animal.y > target.y) {
            animal.y += velocity.y;
            if (animal.y < target.y) animal.y = target.y;
        }

        /* Si el animal llega al target, salimos del estado */
        if (animal.x == target.x && animal.y == target.y) {
            animal.changeStateV2(new IdleAnimalState(animal));
            return;
        }
    }
}
