package org.zoo.vista.visitor;
import org.zoo.modelo.*;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;

public interface Visitor {
    public void visitAnimal(Animal animal);
    public void visitHabitat(Habitat habitat);
    public void visitZoo(Zoo zoo);
    public void visitHabitatPlacementManager(HabitatPlacementManager hpm);
    public void visitAnimalPlacementManager(AnimalPlacementManager apm);
    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay);
    public void visitFoodArea(FoodArea foodArea);
    public void visitTextMessage(TextMessage text);
}
