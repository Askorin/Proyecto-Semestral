package org.zoo.vista;
import org.zoo.*;
import org.zoo.Point;

import java.awt.*;

public interface Visitor {
    public void visitAnimal(Animal animal);
    public void visitHabitat(Habitat habitat);
    public void visitVistaZoo(VistaZoo vistaZoo);
    public void visitHabitatPlacementManager(HabitatPlacementManager hpm);
    public void visitAnimalPlacementManager(AnimalPlacementManager apm);
    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay);
    public void visitFoodArea(FoodArea foodArea);
}
