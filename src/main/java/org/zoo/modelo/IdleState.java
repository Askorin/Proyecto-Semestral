package org.zoo.modelo;

public class IdleState implements State {
    private Animal animal;
    private final int IDLE_DURATION = (int) (Math.random()*3000 + 2000); // entre 2 y 5 s
    private final long initMs;
    private long currentMs;
    public IdleState(Animal animal) {
        this.animal = animal;
        initMs = System.currentTimeMillis();
        currentMs = initMs;
        animal.setSprite(animal.getIdleSprite());
    }
    @Override
    public void stateBehavior() {
        // TODO: Ver una manera de hacerlo sin globaltimer
        currentMs = System.currentTimeMillis();
        long timeElapsed = currentMs - initMs;
        if (timeElapsed >= IDLE_DURATION) {
            animal.changeState(this);
        }
    }
}
