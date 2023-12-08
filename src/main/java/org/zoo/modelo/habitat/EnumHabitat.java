package org.zoo.modelo.habitat;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.utilities.ZooPoint;

// TODO: Mover path a sprite, return ingameSprite, hacerlo igual que food.
public enum EnumHabitat implements MenuItem {
    MEADOW("Meadow",  MeadowHabitat.class, Sprite.MEADOWHABITAT),
    TAIGA("Taiga", TaigaHabitat.class, Sprite.TAIGAHABITAT);
    private final String nombre;
    private final Class<?> tipo;
    private final Sprite inGameSprite;
    EnumHabitat(String nombre, Class<?> tipo, Sprite inGameSprite) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.inGameSprite = inGameSprite;
    }

    public Class<?> getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Sprite getInGameSprite() {
        return inGameSprite;
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
