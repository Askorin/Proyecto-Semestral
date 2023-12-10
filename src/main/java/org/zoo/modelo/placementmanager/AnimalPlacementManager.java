package org.zoo.modelo.placementmanager;

import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.utilities.ZooPoint;
import org.zoo.modelo.characteristics.Drawable;
import org.zoo.visitor.Visitor;

import java.lang.reflect.Field;

/**
 * Clase posicionadora de animales, se identifica por un <code>EnumAnimal</code>
 * @see EnumAnimal
 * @see org.zoo.modelo.animal.Animal
 */
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

        Habitat habitat = getZoo().getHabitatFromPoint(new ZooPoint(getAbsX(), getAbsY()));

        // Coordenadas en las que se posicionará el gato.
        int posX = getAbsX() - width / 2;
        int posY = getAbsY() - height / 2;

        if (habitat != null) {
            ZooPoint habitatAbs = new ZooPoint(habitat.getAbsX(), habitat.getAbsY());
            ZooPoint placementAbs = new ZooPoint(posX, posY);
            ZooPoint relative = ZooPoint.getDifference(placementAbs, habitatAbs);
            habitat.addAnimal(enumAnimal, relative);
        }
    }
    @Override
    public void accept(Visitor v) {
        v.visitAnimalPlacementManager(this);
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
