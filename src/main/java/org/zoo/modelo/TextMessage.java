package org.zoo.modelo;

import org.zoo.modelo.characteristics.Positionable;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TextMessage implements Updatable, Drawable {
    private String text;
    private Habitat ownerHabitat;
    private final long initMs;
    private long currentMs;
    public static final long  LIFETIME = 8000;
    //TODO: TextMessage, por como es actualmente, no deberia recibir ningun parametro mas que el texto...
    // ...pero como necesitamos meterlo en un Updatables, necesitamos algo mayor donde meterlo.
    public TextMessage(Habitat ownerHabitat, String text) {
        this.ownerHabitat = ownerHabitat;
        this.text = text;

        initMs = System.currentTimeMillis();
        currentMs = initMs;
    }
    public long getTimeElapsed() {
        return currentMs - initMs;
    }

    @Override
    public void update() {
        currentMs = System.currentTimeMillis();
        if (getTimeElapsed() >= LIFETIME) {
            ownerHabitat.getContainables().removeComponent(this);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitTextMessage(this);
    }
    @Override
    public int getAbsX() {
        return 0;
    }
    public int getAbsY() {
        return 0;
    }
    public String getText() {
        return text;
    }
}