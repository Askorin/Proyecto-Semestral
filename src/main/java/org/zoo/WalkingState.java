package org.zoo;

//Este estado corresponde a caminar a un punto aleatorio
//es util para tener un comportamiente "por defecto" y no quedarse quieto
public class WalkingState implements State {
    private Animal animal;
    private Point target; //punto aleatorio el cual es a donde se dirige el animal
    private int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingState(Animal animal) {
        this.animal = animal;
        int targetX;
        int targetY;
        while (true) {
            //es importante que el target no esta fuera de los limites del habitat
            targetX = (int) (Math.random() * (animal.ownerHabitat.width - animal.getWidth()));
            targetY = (int) (Math.random()*(animal.ownerHabitat.height - animal.getHeight()));

            //es importante que exista un camino al target donde pueda ir el animal
            //TODO: IMPORTANTE TENER EN CUENTA QUE:
            /*Actualmente el org.zoo.State no calcula si el camino a seguir esta despejado,
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
        target = new Point(targetX, targetY);
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateBehavior() {
        Point direction = Point.getDifference(target, new Point(animal.x, animal.y));
        Point velocity = Utilities.getNormalizedVector(direction, speed);

        //el animal se mueve hasta que coincida con el target
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

        if (animal.x == target.x && animal.y == target.y) {
            animal.changeState(this);
            return;
        }
    }
}
