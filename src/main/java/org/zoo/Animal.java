package org.zoo;

import org.zoo.vista.Drawable;
import org.zoo.vista.Visitor;

import java.awt.*;
public abstract class Animal implements Updatable, Drawable {
    public int x;
    public int y;
    private int width;
    private int height;
    protected Sprite currentSprite;
    protected State currentState;
    protected Habitat ownerHabitat;
    private final long initMs;
    private long currentMs;
    private long hungerInitMs;
    private long hungerCurrentMs;
    private long HUNGER_LIMIT_MS;
    private long HUNGER_MAX_LIMIT_MS;
    public Animal(Habitat habitat, int x , int y) {
        ownerHabitat = habitat;
        this.x = x;
        this.y = y;

        initMs = System.currentTimeMillis();
        currentMs = initMs;
        hungerInitMs = initMs;
        hungerCurrentMs = initMs;
    }
    public void draw(Graphics g, Point absPoint, Visitor v) {
        v.visitAnimal(this, absPoint);

        // int x = absPoint.x+ this.x;
        // int y = absPoint.y + this.y;

        // //Dibujar org.zoo.Hitbox (Borrar luego)
        // if (true) {
        //     g.setColor(Color.RED);
        //     g.drawRect(x, y, width, height);
        // }

        // currentSprite.drawSprite(g, x, y, getWidth(), getHeight(), getTimeElapsed(), 1.0f);
    }
    public void update() {
        currentState.stateBehavior();
        currentMs = System.currentTimeMillis();
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
    public long getHungerTimeElapsed() {
        return hungerCurrentMs - hungerInitMs;
    }
    public void restartHungerTimeElapsed() {
        hungerInitMs = System.currentTimeMillis();
        hungerCurrentMs = initMs;
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
    protected void setWidth(int width) {this.width = width;}
    public int getHeight() {
        return height;
    }
    protected void setHeight(int height) {this.height = height;}

    public Sprite getCurrentSprite() {
        return currentSprite;
    }
}
