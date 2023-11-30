package org.zoo.vista.visitor;
import org.zoo.modelo.*;
import org.zoo.modelo.animal.Animal;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.FoodPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;

public interface Visitor {
    void visitAnimal(Animal animal);
    void visitHabitat(Habitat habitat);
    void visitZoo(Zoo zoo);
    void visitHabitatPlacementManager(HabitatPlacementManager hpm);
    void visitAnimalPlacementManager(AnimalPlacementManager apm);
    void visitFoodPlacementManager(FoodPlacementManager fpm);
    void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay);
    void visitFoodArea(FoodArea foodArea);
    void visitTextMessageManager(TextMessageManager manager);
    void visitTextMessage(TextMessage text);
}
