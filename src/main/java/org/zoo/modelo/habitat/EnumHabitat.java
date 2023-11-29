package org.zoo.modelo.habitat;

import org.zoo.modelo.Sprite;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.utilities.ZooPoint;

// TODO: Mover path a sprite, return ingameSprite, hacerlo igual que food.
public enum EnumHabitat {
    MEADOW("Meadow", "/MeadowHabitat.png", "/habitat.png", MeadowHabitat.class, Sprite.MEADOWHABITAT),
    TAIGA("Taiga", "/TaigaHabitat.png", "/habitat.png", TaigaHabitat.class, Sprite.TAIGAHABITAT);
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

    public Habitat newInstance(Positionable owner, ZooPoint p) {
        Habitat habitat = null;
        try {
            habitat = (Habitat) tipo.getDeclaredConstructor(Positionable.class, ZooPoint.class).newInstance(owner, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return habitat;
    }
}
