//Este estado corresponde a caminar a un punto aleatorio
//es util para tener un comportamiente "por defecto" y no quedarse quieto
public class WalkingState implements State {
    private int targetX; //punto aleatorio el cual es a donde se dirige el animal
    private int targetY;
    private int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingState(Animal animal) {
        while (true) {
            //es importante que el target no esta fuera de los limites del habitat
            targetX = (int) (Math.random() * (animal.ownerHabitat.width - animal.getWidth()));
            targetY = (int) (Math.random()*(animal.ownerHabitat.height - animal.getHeight()));

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
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
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
            return;
        }
    }
    //método para devolver la componente horizontal de la velocidad con norma = speed
    private int getSpeedX(Animal animal) {
        double speedXnotNorm = (double)(targetX - animal.x);
        double speedYnotNorm = (double)(targetY - animal.y);
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                             +  speedYnotNorm * speedYnotNorm);
        double speedX = (speed * speedXnotNorm) / norm;
        return  (int)speedX;
    }
    //método para devolver la componente vertical de la velocidad con norma = speed
    private int getSpeedY(Animal animal) {
        double speedXnotNorm = (double)(targetX - animal.x);
        double speedYnotNorm = (double)(targetY - animal.y);
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                +  speedYnotNorm * speedYnotNorm);
        double speedY = (speed * speedYnotNorm) / norm;
        return  (int)speedY;
    }
}
