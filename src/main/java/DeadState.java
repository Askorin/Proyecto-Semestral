public class DeadState implements State {
    public DeadState(Animal animal) {
        animal.setSprite(Sprite.FISH_INGAME);
    }
    @Override
    public void stateBehavior(Animal animal) {

    }
}
