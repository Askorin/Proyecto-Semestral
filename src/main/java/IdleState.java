public class IdleState implements State {
    int idleDuration = (int) (Math.random()*900 + 100); // entre 1 y 10 s
    int timeElapsed = 0;
    @Override
    public void stateBehavior(Animal animal) {
        timeElapsed += GlobalTimer.MS_PER_FRAME;
        if (timeElapsed >= idleDuration) {
            animal.currentState = new WalkingState(animal);
        }
    }
}
