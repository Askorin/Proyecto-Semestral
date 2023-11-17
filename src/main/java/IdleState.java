public class IdleState implements State {
    private int idleDuration = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private int timeElapsed = 0;
    public IdleState(Animal animal) {
        animal.setSprite(animal.getIdleSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
        timeElapsed += GlobalTimer.MS_PER_FRAME;
        if (timeElapsed >= idleDuration) {
            animal.changeState(this);
            return;
        }
    }
}
