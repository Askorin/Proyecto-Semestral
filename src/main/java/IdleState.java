public class IdleState implements State {
    private int idleDuration = (int) (Math.random()*900 + 100); // entre 1 y 10 s
    private int timeElapsed = 0;
    public IdleState(Animal animal) {
        animal.setSprite(animal.getIdleSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
        timeElapsed += GlobalTimer.MS_PER_FRAME;
        if (timeElapsed >= idleDuration) {
            animal.currentState = new WalkingState(animal);
            return;
        }
    }
}
