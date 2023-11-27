package org.zoo.modelo.animal;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.states.WalkingState;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Point;

public class Gato extends Animal {
    public static int width = 80;
    public static int height = 80;
    public static float minTemperature = 10;
    public static float maxTemperature = 35;
    public static EnumFood[] prefferedFood = {EnumFood.FISH};
    public static EnumAnimal[] invalidCompanion = {};
    private long HUNGER_LIMIT_MS = 5000;
    private long HUNGER_MAX_LIMIT_MS = 18000;
    public Gato(Habitat habitat, Point p) {
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
        currentState =  new WalkingState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.CAT_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.CAT_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.CAT_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.CAT_HUNGRY;}
}
