package org.zoo.modelo.habitat;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.food.Food;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.characteristics.Positionable;

public class MeadowHabitat extends Habitat {
    public static int width = 64*4;
    public static int height = 64*4;
    public static float temperature = 25;
    public MeadowHabitat(Positionable owner) {
        super(owner);
        // Esto para coordinar parametros estaticos con los del padre
        setWidth(width);
        setHeight(height);
        setTemperature(temperature);
        setHabitatSprite(Sprite.MEADOWHABITAT);

        FoodArea foodArea = new FoodArea(this,48 * 4, 0, 16 * 4, 64 * 4);
        getContainables().addComponent(foodArea);
        foodArea.add(Food.FISH);
        foodArea.add(Food.FISH);
    }
}
