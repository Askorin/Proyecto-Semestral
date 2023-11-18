//Este estado corresponde a caminar a un punto aleatorio
//es util para tener un comportamiente "por defecto" y no quedarse quieto
public class WalkingState implements State {
    private int targetX; //punto aleatorio el cual es a donde se dirige el animal
    private int targetY;
    private int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingState(Animal animal) {
        //es importante que el target no esta fuera de los limites del habitat
        targetX = (int) (Math.random()*(animal.ownerHabitat.width - animal.getWidth()));
        targetY = (int) (Math.random()*(animal.ownerHabitat.height - animal.getHeight()));
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
