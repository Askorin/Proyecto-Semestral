package org.zoo;

import java.awt.*;
import java.util.ArrayList;

//Almacena información de una animación, pero sin cargarla graficamente
public enum Sprite {
    CAT_IDLE("src/main/resources/CatIdle.png", 6, 150, 13*4, 9*4),
    CAT_WALK("src/main/resources/CatWalk.png", 2, 90, 15*4, 9*4),
    CAT_EAT("src/main/resources/CatEat.png", 4, 90, 16*4, 2*4),
    CAT_HUNGRY("src/main/resources/CatHungry.png", 6, 220, 13*4, 24*4),
    MEADOWHABITAT("src/main/resources/meadowHabitat.png", 1, 1, 37*4, 40*4),
    FISH_INGAME("src/main/resources/FishInGame.png", 1, 1, 4*4, 4*4);
    private String path;
    private int framesNumber; //Numero de frames
    private int timePerFrame; //Velocidad (mas bien el reciproco) de la animacion
    private int centerX; //Centro de la animacion, es util para casos que el centro del sprite no es en la mitad
    private int centerY;

    Sprite(String path, int framesNumber, int timePerFrame) {
        this.path = path;
        this.framesNumber = framesNumber;
        this.timePerFrame = timePerFrame;

        centerX = 5;
        centerY = 5;
    }

    //Por defecto, el centro de un sprite es al medio, pero podemos entregarle un centro personalizado
    Sprite(String path, int framesNumber, int timePerFrame, int centerX, int centerY) {
        this(path, framesNumber, timePerFrame);
        this.centerX = centerX;
        this.centerY = centerY;
    }
    public int getCenterX() {
        return centerX;
    }
    public int getCenterY() {
        return centerY;
    }
    public int getFramesNumber() {
        return framesNumber;
    }
    public String getPath() {
        return path;
    }
    public int getTimePerFrame() {
        return timePerFrame;
    }
}
