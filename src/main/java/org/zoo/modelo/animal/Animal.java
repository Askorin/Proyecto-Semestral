package org.zoo.modelo.animal;

import org.zoo.modelo.*;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.food.Food;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.modelo.states.*;
import org.zoo.utilities.Hitbox;
import org.zoo.utilities.Point;
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
    private Food[] prefferedFood;
    public Sprite currentSprite;
    public State currentState;
    public Habitat ownerHabitat;
    private final long initMs;
    private long currentMs;
    private long spriteInitMs;
    private long spriteCurrentMs;
    private long hungerInitMs;
    private long hungerCurrentMs;
    private long HUNGER_LIMIT_MS;
    private long HUNGER_MAX_LIMIT_MS;

    public Animal(Habitat ownerHabitat, Point p) {
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
        currentState.stateBehavior();

        currentMs = System.currentTimeMillis();
        spriteCurrentMs = System.currentTimeMillis();
        hungerCurrentMs = System.currentTimeMillis();
    }

    //Administrador de estados, corresponde al grafo de estados en una maquina de estados finitos (No sé de que hablo)
    public void changeState(State currentState) {
        if (getHungerTimeElapsed() >= getHungerMaxLimitMs()) {
            //Default
            this.currentState = new DeadState(this);
            return;
        }
        if (getHungerTimeElapsed() >= getHungerLimitMs()) {
            if (currentState.getClass() == EatingState.class) {
                this.currentState = new StarvingState(this);
                return;
            }
            if (currentState.getClass() == StarvingState.class) {
                this.currentState = new GatheringState(this);
                return;
            }
            if (currentState.getClass() == GatheringState.class) {
                this.currentState = new EatingState(this);
                return;
            }
            //Default
            this.currentState = new StarvingState(this);
            return;
        }
        if (currentState.getClass() == WalkingState.class) {
            this.currentState = new IdleState(this);
            return;
        }
        //Default
        this.currentState = new WalkingState(this);
        return;
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

    public Food[] getPrefferedFood() {
        return prefferedFood;
    }
    protected void setPrefferedFood(Food[] prefferedFood) {
        this.prefferedFood = prefferedFood;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public State getCurrentState() {return currentState;}
    public Hitbox getHitbox() {
        return hitbox;
    }
}
