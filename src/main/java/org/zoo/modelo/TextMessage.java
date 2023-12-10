package org.zoo.modelo;

import org.zoo.modelo.characteristics.Positionable;
import org.zoo.modelo.characteristics.Updatable;
import org.zoo.modelo.habitat.Habitat;
import org.zoo.vista.Drawable;
import org.zoo.vista.visitor.Visitor;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Clase logica para enviar mensajes que contengan solo texto,
 * en concreto, una instancia de esta clase corresponde a un mensaje.
 * @see TextMessageManager
 */
public class TextMessage implements Updatable, Drawable {
    /**
     * Texto que contiene el mensaje
     */
    private final String text;
    /**
     * En que tiempo (en ms) se creo el mensaje
     */
    private final long initMs;
    /**
     * Tiempo (en ms) actual
     */
    private long currentMs;
    /**
     * Tiempo de vida en que debe permanecer el mensaje
     */
    public static final long  LIFETIME = 8000;

    /**
     * Constructor unico de un mensaje
     * @param text Texto que va a contener el mensaje
     */
    public TextMessage(String text) {
        this.text = text;

        initMs = System.currentTimeMillis();
        currentMs = initMs;

        TextMessageManager.addTextMessage(this);
    }

    /**
     * Permite cronometrizar cuando tiempo lleva el mensaje desde su creacion
     * @return Tiempo (en ms) que ha pasado de la creacion del mensaje
     */
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

    /**
     * Permite saber el contenido del mensaje
     * @return Texto que contiene el mensaje
     */
    public String getText() {
        return text;
    }
}