public class EatingState implements State {
    private final int EATING_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public EatingState(Animal animal) {
        initMs = System.currentTimeMillis();
        currentMs = initMs;
        animal.setSprite(animal.getEatSprite());
    }
    @Override
    public void stateBehavior(Animal animal) {
        // TODO: Ver una manera de hacerlo sin globaltimer
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= EATING_DURATION) {
            animal.changeState(this);
        }
    }
}
