package org.zoo.modelo.placementmanager;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.ZooPoint;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

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
        // Field fieldWidth = null;
        // Field fieldHeight = null;
        // int width = 0, height = 0;
        // try {
        //     fieldWidth = enumAnimal.getTipo().getField("width");
        //     fieldHeight = enumAnimal.getTipo().getField("height");
        // } catch (NoSuchFieldException e) {}
        // try {
        //     width = fieldWidth.getInt(null);
        //     height = fieldHeight.getInt(null);
        // } catch (IllegalAccessException e) {}

        Habitat habitat = getZoo().getHabitatFromPoint(new ZooPoint(getAbsX(), getAbsY()));

        // Coordenadas en las que se posicionará el gato.
        // int posX = getAbsX() - width / 2;
        // int posY = getAbsY() - height / 2;

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
