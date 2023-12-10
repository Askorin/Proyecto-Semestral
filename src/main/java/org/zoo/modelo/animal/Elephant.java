package org.zoo.modelo.animal;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.states.WalkingAnimalState;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

/**
 * Modelo logico de un elefante en el zoologico, dentro de un <code>Habitat</code>
 * Es hijo de la clase <code>Animal</code>
 * @see Animal
 * @see EnumAnimal
 */
public class Elephant extends Animal {
    public static int width = 140;
    public static int height = 100;
    /**
     * Temperatura minima de <code>Habitat</code> que el <code>Animal</code> acepta
     */
    public static float minTemperature = 5;
    /**
     * Temperatura maxima de <code>Habitat</code> que el <code>Animal</code> acepta
     */
    public static float maxTemperature = 35;
    /**
     * Lista de comida que el <code>Animal</code> consume
     * El orden importa, las comidas preferidas deberian ir pimero.
     */
    public static EnumFood[] prefferedFood = {EnumFood.LEAVES, EnumFood.BANANA};
    /**
     * Lista de animales con cuales el <code>Animal</code> no puede compartir habitat.
     * No es necesario ser simetrico: A no convive con B, B si convive con A.
     */
    public static EnumAnimal[] invalidCompanion = {};
    /**
     * Tiempo (en ms) maximo que pasa sin estar hambriento
     */
    private final long HUNGER_LIMIT_MS = 20000;
    /**
     * Tiempo (en ms) que puede pasar sin comer, antes de morir
     */
    private final long HUNGER_MAX_LIMIT_MS = 60000;
    public Elephant(Habitat habitat, ZooPoint p) {
        super(habitat, p);
        //Hay que entregar los parametros del hijo para sobreescribir las parametros del padre
        setWidth(width);
        setHeight(height);
        this.hitbox = new Hitbox(x, y, getWidth(), getHeight());

        setMinTemperature(minTemperature);
        setMaxTempperature(maxTemperature);

        setPrefferedFood(prefferedFood);
        setInvalidCompanion(invalidCompanion);

        setHungerLimitMs(HUNGER_LIMIT_MS);
        setHungerMaxLimitMs(HUNGER_MAX_LIMIT_MS);

        //Importante que el estado se defina DESPUES de las dimensiones
        changeState(new WalkingAnimalState(this));
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.ELEPHANT_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.ELEPHANT_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.ELEPHANT_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.ELEPHANT_HUNGRY;}
}