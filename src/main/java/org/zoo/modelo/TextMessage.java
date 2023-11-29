package org.zoo.modelo;

import org.zoo.modelo.characteristics.Positionable;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TextMessage implements Updatable, Drawable {
    private final String text;
    private final long initMs;
    private long currentMs;
    public static final long  LIFETIME = 8000;

    // ¡IMPORTANTE!: TextMessage se pone solito en el Manager, no es necesario colocarlo uno
    public TextMessage(String text) {
        this.text = text;

        initMs = System.currentTimeMillis();
        currentMs = initMs;

        TextMessageManager.addTextMessage(this);
    }
    public long getTimeElapsed() {
        return currentMs - initMs;
    }

    @Override
    public void update() {
        currentMs = System.currentTimeMillis();
        if (getTimeElapsed() >= LIFETIME) {
            TextMessageManager.removeTextMessage(this);
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