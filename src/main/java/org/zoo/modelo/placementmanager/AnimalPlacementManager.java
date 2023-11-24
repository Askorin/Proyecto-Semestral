package org.zoo.modelo.placementmanager;

import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

import java.lang.reflect.Field;

public class AnimalPlacementManager extends PlacementManager<EnumAnimal> implements Drawable {
    private EnumAnimal enumAnimal;
    public AnimalPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumAnimal enumAnimal) {
        this.enumAnimal = enumAnimal;
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumAnimal = null;
        setActivo(false);
    }

    @Override
    public void place() {
        Field fieldWidth = null;
        Field fieldHeight = null;
        int width = 0, height = 0;
        try {
            fieldWidth = enumAnimal.getTipo().getField("width");
            fieldHeight = enumAnimal.getTipo().getField("height");
        } catch (NoSuchFieldException e) {}
        try {
            width = fieldWidth.getInt(null);
            height = fieldHeight.getInt(null);
        } catch (IllegalAccessException e) {}
    }
    @Override
    public void accept(Visitor v) {
        v.visitAnimalPlacementManager(this);
        // if (isActivo()) {
        //     enumAnimal.getSprite().drawSprite(g, getMouseX(), getMouseY(), 0, 0, 0, 0.45f);
        // }
    }

    @Override
    public int getAbsX() {
        return getX();
    }
    public int getAbsY() {
        return getY();
    }

    public EnumAnimal getEnumAnimal() {
        return enumAnimal;
    }
}
