public class GatheringState implements State {
    private int targetX;
    private int targetY;
    private int speed = (int) (Math.random()*3 + 3); // entre 3 y 6;
    public GatheringState(Animal animal) {
        FoodContainer targetContainer = FoodContainer.searchFoodContainer(animal.ownerHabitat);
        if (targetContainer == null) {
            System.err.println("TARGETCONTAINER NULL, OJITO"); //TODO: Ver que hacer en este caso
            targetX = animal.x;
            targetY = animal.y;
        }
        else {
            int x = animal.x; int y = animal.y;
            int width = animal.getWidth(); int height = animal.getHeight();
            targetX = targetContainer.getCloserPointToHitbox_coordX(x, y, width, height);
            targetY = targetContainer.getCloserPointToHitbox_coordY(x, y, width, height);
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
