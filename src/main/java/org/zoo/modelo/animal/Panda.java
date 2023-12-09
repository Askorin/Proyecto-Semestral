package org.zoo.modelo.animal;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.states.GatheringAnimalState;
import org.zoo.modelo.states.WalkingAnimalState;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;

public class Panda extends Animal {
    public static int width = 70;
    public static int height = 50;
    public static float minTemperature = 0;
    public static float maxTemperature = 25;
    public static EnumFood[] prefferedFood = {EnumFood.LEAVES};
    public static EnumAnimal[] invalidCompanion = {EnumAnimal.LION};
    private final long HUNGER_LIMIT_MS = 18000;
    private final long HUNGER_MAX_LIMIT_MS = 60000;
    public Panda(Habitat habitat, ZooPoint p) {
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
    public Sprite getIdleSprite() {return Sprite.PANDA_IDLE;}
    @Override
    public Sprite getWalkSprite() {return Sprite.PANDA_WALK;}
    @Override
    public Sprite getEatSprite() {return Sprite.PANDA_EAT;}
    @Override
    public Sprite getHungrySprite() {return Sprite.PANDA_HUNGRY;}
}
