package org.zoo.vista;
import org.zoo.*;
import org.zoo.Point;

import java.awt.*;

public interface Visitor {
    public void visitAnimal(Animal animal, Point absPoint);
    public void visitHabitat(Habitat habitat, Point absPoint);

    public void visitVistaZoo(VistaZoo vistaZoo, Point absPoint);
    public void visitHabitatPlacementManager(HabitatPlacementManager hpm, Point absPoint);
    public void visitAnimalPlacementManager(AnimalPlacementManager apm, Point absPoint);
    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay, Point absPoint);
    public void visitFoodArea(FoodArea foodArea, Point absPoint);
}
