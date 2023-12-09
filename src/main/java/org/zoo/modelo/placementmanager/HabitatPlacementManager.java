package org.zoo.modelo.placementmanager;

import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

import java.lang.reflect.Field;


public class HabitatPlacementManager extends PlacementManager<EnumHabitat> implements Drawable {
    private EnumHabitat enumHabitat;
    public HabitatPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumHabitat enumHabitat) {
        this.enumHabitat = enumHabitat;
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumHabitat = null;
        setActivo(false);
    }

    @Override
    public void place() {
        Field fieldWidth = null;
        Field fieldHeight = null;
        int width = 0, height = 0;
        try {
            fieldWidth = enumHabitat.getTipo().getField("width");
            fieldHeight = enumHabitat.getTipo().getField("height");
        } catch (NoSuchFieldException e) {}
        try {
            width = fieldWidth.getInt(null);
            height = fieldHeight.getInt(null);
        } catch (IllegalAccessException e) {}

        int posX = getX() - width / 2;
        int posY = getY() - height / 2;
        getZoo().addHabitat(posX, posY, enumHabitat);
    }


    @Override
    public void accept(Visitor v) {
        v.visitHabitatPlacementManager(this);
    }

    public int getAbsX() {
        return getX();
    }
    public int getAbsY() {
        return getY();
    }

    public EnumHabitat getEnumHabitat() {
        return enumHabitat;
    }
}
