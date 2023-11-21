package org.zoo.vista;
import org.zoo.*;
import org.zoo.Point;

import java.awt.*;

public interface Visitor {
    public void visitAnimal(Animal animal, Graphics g, Point absPoint);
    public void visitHabitat(Habitat habitat, Graphics g, Point absPoint);

    public void visitVistaZoo(VistaZoo vistaZoo, Graphics g, Point absPoint);
    public void visitHabitatPlacementManager(HabitatPlacementManager hpm, Graphics g, Point absPoint);
    public void visitAnimalPlacementManager(AnimalPlacementManager apm, Graphics g, Point absPoint);
    public void visitFoodDisplay(FoodArea.FoodDisplay foodDisplay, Graphics g, Point absPoint);
    public void visitFoodArea(FoodArea foodArea, Graphics g, Point absPoint);
}
