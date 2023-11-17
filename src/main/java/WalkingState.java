public class WalkingState implements State {
    private int targetX;
    private int targetY;
    private int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public WalkingState(Animal animal) {
        targetX = (int) (Math.random()*(animal.ownerHabitat.width - animal.getWidth()));
        targetY = (int) (Math.random()*(animal.ownerHabitat.height - animal.getHeight()));
        animal.setSprite(animal.getWalkSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
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
    private int getSpeedX(Animal animal) {
        double speedXnotNorm = (double)(targetX - animal.x);
        double speedYnotNorm = (double)(targetY - animal.y);
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                             +  speedYnotNorm * speedYnotNorm);
        double speedX = (speed * speedXnotNorm) / norm;
        return  (int)speedX;
    }
    private int getSpeedY(Animal animal) {
        double speedXnotNorm = (double)(targetX - animal.x);
        double speedYnotNorm = (double)(targetY - animal.y);
        double norm = Math.sqrt(speedXnotNorm * speedXnotNorm
                +  speedYnotNorm * speedYnotNorm);
        double speedY = (speed * speedYnotNorm) / norm;
        return  (int)speedY;
    }
}
