public class StarvingState implements State {
    public StarvingState(Animal animal) {
    }
    @Override
    public void stateBehavior(Animal animal) {
        FoodArea targetFood = FoodArea.searchFoodContainer(animal.ownerHabitat);

        //Este estado se mantiene hasta que encuentra comida
        if (targetFood != null) {
            if (targetFood.find(Food.FISH)) {
                animal.changeState(this);
                return;
            }
        }
        //A menos que haya pasado mucho tiempo sin comer
        if (animal.getHungerTimeElapsed() >= animal.getHungerMaxLimitMs()) {
            animal.changeState(this);
            return;
        }
        animal.setSprite(animal.getHungrySprite());
    }
}
