package org.zoo.modelo.animal;

import org.zoo.modelo.*;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.states.*;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.ZooPoint;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

public abstract class Animal implements Updatable, Drawable {
    public int x;
    public int y;
    protected Hitbox hitbox;
    private int width;
    private int height;
    private float minTemperature;
    private float maxTempperature;
    /* Nota: el orden importa, el animal va a preferir [i] antes que [i+1] */
    private EnumFood[] prefferedFood;
    /* Nota: no es necesario ser simetrico, Conejo NO convive con Zorro, Zorro SI convive con Conejo */
    private EnumAnimal[] invalidCompanion;
    private Sprite currentSprite;
    public AnimalState currentAnimalState;
    public Habitat ownerHabitat;
    private final long initMs;
    private long currentMs;
    private long spriteInitMs;
    private long spriteCurrentMs;
    private long hungerInitMs;
    private long hungerCurrentMs;
    private long HUNGER_LIMIT_MS;
    private long HUNGER_MAX_LIMIT_MS;

    public Animal(Habitat ownerHabitat, ZooPoint p) {
        this.ownerHabitat = ownerHabitat;
        this.x = p.x;
        this.y = p.y;

        initMs = System.currentTimeMillis();
        currentMs = initMs;
        spriteInitMs = initMs;
        spriteCurrentMs = initMs;
        hungerInitMs = initMs;
        hungerCurrentMs = initMs;
    }

    public void accept(Visitor v) {
        v.visitAnimal(this);
    }

    @Override
    public int getAbsX() {
        return x + ownerHabitat.getAbsX();
    }

    public int getAbsY() {
        return y + ownerHabitat.getAbsY();
    }

    public void update() {
        currentAnimalState.stateUpdate();

        currentMs = System.currentTimeMillis();
        spriteCurrentMs = System.currentTimeMillis();
        hungerCurrentMs = System.currentTimeMillis();
    }

    public void changeStateV2(AnimalState newState) {
        this.currentAnimalState = newState;
    }
    //Administrador de estados, corresponde al grafo de estados en una maquina de estados finitos (No sé de que hablo)
    // TODO: Refactorizar y arreglar bug de comida.
    public void changeState(AnimalState currentAnimalState) {
        if (getHungerTimeElapsed() >= getHungerMaxLimitMs()) {
            //Default
            this.currentAnimalState = new DeadAnimalState(this);
            return;
        }
        if (getHungerTimeElapsed() >= getHungerLimitMs()) {
            if (currentAnimalState.getClass() == EatingAnimalState.class) {
                this.currentAnimalState = new StarvingAnimalState(this);
                return;
            }
            if (currentAnimalState.getClass() == StarvingAnimalState.class) {
                this.currentAnimalState = new GatheringAnimalState(this);
                return;
            }
            if (currentAnimalState.getClass() == GatheringAnimalState.class) {
                this.currentAnimalState = new EatingAnimalState(this);
                return;
            }
            //Default
            this.currentAnimalState = new StarvingAnimalState(this);
            return;
        }
        if (currentAnimalState.getClass() == WalkingAnimalState.class) {
            this.currentAnimalState = new IdleAnimalState(this);
            return;
        }
        //Default
        this.currentAnimalState = new WalkingAnimalState(this);
    }

    public static boolean doGetAlong(Animal animal1, Animal animal2) {
        for (EnumAnimal a: animal1.getInvalidCompanion()) {
            if (a.getTipo() == animal2.getClass()) {
                if (animal2.getCurrentState().getClass() != DeadAnimalState.class) {
                    return false;
                }
            }
        }
        for (EnumAnimal a: animal2.getInvalidCompanion()) {
            if (a.getTipo() == animal1.getClass()) {
                if (animal1.getCurrentState().getClass() != DeadAnimalState.class) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO: Actualmente es necesario que los hijos definan metodos para acceder a sus sprites
    public abstract Sprite getIdleSprite();

    public abstract Sprite getWalkSprite();

    public abstract Sprite getEatSprite();

    public abstract Sprite getHungrySprite();

    public long getTimeElapsed() {
        return currentMs - initMs;
    }

    public long getSpriteTimeElapsed() {
        return spriteCurrentMs - spriteInitMs;
    }

    public void restartSpriteTimeElapsed() {
        spriteInitMs = System.currentTimeMillis();
        spriteCurrentMs = spriteInitMs;
    }

    public long getHungerTimeElapsed() {
        return hungerCurrentMs - hungerInitMs;
    }

    public void restartHungerTimeElapsed() {
        hungerInitMs = System.currentTimeMillis();
        hungerCurrentMs = hungerInitMs;
    }

    public long getHungerLimitMs() {
        return HUNGER_LIMIT_MS;
    }

    protected void setHungerLimitMs(long timeMs) {
        this.HUNGER_LIMIT_MS = timeMs;
    }

    public long getHungerMaxLimitMs() {
        return HUNGER_MAX_LIMIT_MS;
    }

    protected void setHungerMaxLimitMs(long timeMs) {
        this.HUNGER_MAX_LIMIT_MS = timeMs;
    }

    public void setSprite(Sprite sprite) {
        currentSprite = sprite;
    }

    public int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    protected void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTempperature() {
        return maxTempperature;
    }

    protected void setMaxTempperature(float maxTempperature) {
        this.maxTempperature = maxTempperature;
    }

    public EnumFood[] getPrefferedFood() {
        return prefferedFood;
    }
    protected void setPrefferedFood(EnumFood[] prefferedFood) {
        this.prefferedFood = prefferedFood;
    }
    public EnumAnimal[] getInvalidCompanion() {
        return invalidCompanion;
    }
    protected void setInvalidCompanion(EnumAnimal[] invalidCompanion) {
        this.invalidCompanion = invalidCompanion;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public AnimalState getCurrentState() {return currentAnimalState;}
    public Hitbox getHitbox() {
        return hitbox;
    }
}
