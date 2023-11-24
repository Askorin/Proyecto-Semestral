package org.zoo.modelo.habitat;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.characteristics.Positionable;

// TODO: Reconsiderar todo esto.
public enum EnumHabitat {
    MEADOW("Meadow", "/meadowHabitat.png", "/habitat.png", MeadowHabitat.class, Sprite.MEADOWHABITAT);
    private final String nombre;
    private final String path;
    private final String labelPath;
    private final Class<?> tipo;
    private final Sprite sprite;
    EnumHabitat(String nombre, String path, String labelPath, Class<?> tipo, Sprite sprite) {
        this.nombre = nombre;
        this.path = path;
        this.labelPath = labelPath;
        this.tipo = tipo;
        this.sprite = sprite;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLabelPath() {
        return labelPath;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Habitat newInstance(Positionable owner) {
        Habitat habitat = null;
        try {
            habitat = (Habitat) tipo.getDeclaredConstructor(Positionable.class).newInstance(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return habitat;
    }
}
