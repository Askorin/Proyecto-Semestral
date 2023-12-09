package org.zoo.modelo.habitat;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.food.FoodArea;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

public class ForestHabitat extends Habitat {
    public static int width = 96*4;
    public static int height = 80*4;
    public static float temperature = 10;

    public ForestHabitat(Positionable owner, ZooPoint p) {
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
        this.absHitbox = new Hitbox(x, y, getWidth(), getHeight());

        // Y esto es para coordinar el sprite
        setHabitatSprite(Sprite.FORESTHABITAT);

        FoodArea foodArea = new FoodArea(this, 80 * 4 + 8, 0 + 8, 16 * 4 - 16, 80 * 4 - 16);
        getContainables().addComponent(foodArea);
    }
}
