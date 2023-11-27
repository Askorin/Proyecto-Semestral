package org.zoo.modelo.animal;

import org.zoo.modelo.food.Food;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.states.WalkingState;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Point;

public class Elephant extends Animal {
    public static int width = 140;
    public static int height = 100;
    public static float minTemperature = 0;
    public static float maxTemperature = 25;
    public static Food[] prefferedFood = {Food.LEAVES};
    public static EnumAnimal[] invalidCompanion = {EnumAnimal.GATO};
    private long HUNGER_LIMIT_MS = 8000;
    private long HUNGER_MAX_LIMIT_MS = 24000;
    public Elephant(Habitat habitat, Point p) {
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
    public Sprite getIdleSprite() {return Sprite.ELEPHANT_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.ELEPHANT_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.ELEPHANT_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.ELEPHANT_HUNGRY;}
}