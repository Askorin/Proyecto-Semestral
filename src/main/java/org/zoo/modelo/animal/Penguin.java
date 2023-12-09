package org.zoo.modelo.animal;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.states.WalkingAnimalState;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

public class Penguin extends Animal {
    public static int width = 40;
    public static int height = 80;
    public static float minTemperature = -20;
    public static float maxTemperature = 0;
    public static EnumFood[] prefferedFood = {EnumFood.FISH};
    public static EnumAnimal[] invalidCompanion = {EnumAnimal.LION};;
    private final long HUNGER_LIMIT_MS = 8000;
    private final long HUNGER_MAX_LIMIT_MS = 25000;
    public Penguin(Habitat habitat, ZooPoint p) {
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
        currentAnimalState =  new WalkingAnimalState(this);
    }

    @Override
    public Sprite getIdleSprite() {return Sprite.PENGUIN_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.PENGUIN_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.PENGUIN_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.PENGUIN_HUNGRY;}
}
