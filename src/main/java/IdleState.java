public class IdleState implements State {
    private final int IDLE_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public IdleState(Animal animal) {
        initMs = System.currentTimeMillis();
        currentMs = initMs;
        animal.setSprite(animal.getIdleSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
        // TODO: Ver una manera de hacerlo sin globaltimer
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= IDLE_DURATION) {
            animal.changeState(this);
        }
    }
}
