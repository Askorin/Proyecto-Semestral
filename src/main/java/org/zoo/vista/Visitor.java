package org.zoo.vista;
import org.zoo.modelo.*;

public interface Visitor {
    public void visitAnimal(Animal animal);
    public void visitHabitat(Habitat habitat);
    public void visitZoo(Zoo zoo);
    public void visitHabitatPlacementManager(HabitatPlacementManager hpm);
    public void visitAnimalPlacementManager(AnimalPlacementManager apm);
    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay);
    public void visitFoodArea(FoodArea foodArea);
}
