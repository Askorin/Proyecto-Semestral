package org.zoo.modelo.placementmanager;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.ZooPoint;
import org.zoo.vista.Drawable;
import org.zoo.visitor.Visitor;

/**
 * Clase posicionadora de comida, se identifica por un <code>EnumAnimal</code>
 * @see EnumFood
 * @see org.zoo.modelo.food.FoodArea.FoodDisplay
 */
public class FoodPlacementManager extends PlacementManager<EnumFood> implements Drawable {
    private EnumFood enumFood;
    public FoodPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumFood enumFood) {
        this.enumFood = enumFood;
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumFood = null;
        setActivo(false);
    }

    @Override
    public void place() {
        Habitat habitat = getZoo().getHabitatFromPoint(new ZooPoint(getAbsX(), getAbsY()));

        if (habitat != null) {
            FoodArea foodArea = habitat.getFoodAreaFromPoint(new ZooPoint(getAbsX(), getAbsY()));
            if (foodArea != null) {
                foodArea.add(enumFood);
            }
        }
    }
    @Override
    public void accept(Visitor v) {
        v.visitFoodPlacementManager(this);
    }

    @Override
    public int getAbsX() {
        return getX();
    }
    public int getAbsY() {
        return getY();
    }

    public EnumFood getEnumFood() {
        return enumFood;
    }
}
