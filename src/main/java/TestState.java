public class TestState implements State {
    int speedX = 5;
    int speedY = 5;
    @Override
    public void stateBehavior(Animal animal) {
        animal.x += speedX;
        if (animal.x + animal.width > animal.ownerHabitat.width || animal.x < 0) {
            speedX *= -1;
        }
        animal.y += speedY;
        if (animal.y + animal.height > animal.ownerHabitat.height || animal.y < 0) {
            speedY *= -1;
        }
    }
}
