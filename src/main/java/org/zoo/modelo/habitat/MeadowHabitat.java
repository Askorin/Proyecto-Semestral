package org.zoo.modelo.habitat;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.food.Food;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Point;

public class MeadowHabitat extends Habitat {
    public static int width = 64*4;
    public static int height = 64*4;
    public static float temperature = 5;
  
    public MeadowHabitat(Positionable owner, Point p) {
        super(owner, p);
        // Esto para coordinar ancho y alto estático con el provisto por el padre.
        setWidth(width);
        setHeight(height);
        setTemperature(temperature);

        /*
         * Creamos la Hitbox del habitat, tener en cuenta que es irrelevante ocupar
         * las coordenadas relativas al owner (Zoo) o las coordenadas absolutas,
         * la colisión se calculará de manera correcta de igual manera.
         * Si en algún futuro se desea ocupar las coordenadas absolutas por alguna
         * razón, se puede crear Hitbox(getAbsX(), getAbsY()...)
         */
        this.hitbox = new Hitbox(x, y, getWidth(), getHeight());

        // Y esto es para coordinar el sprite
        setHabitatSprite(Sprite.MEADOWHABITAT);

        FoodArea foodArea = new FoodArea(this,48 * 4, 0, 16 * 4, 64 * 4);
        getContainables().addComponent(foodArea);
        foodArea.add(Food.FISH);
        foodArea.add(Food.FISH);
    }
}
