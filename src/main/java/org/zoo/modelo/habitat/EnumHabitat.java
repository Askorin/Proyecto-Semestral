package org.zoo.modelo.habitat;

import org.zoo.modelo.MenuItem;
import org.zoo.modelo.Sprite;
import org.zoo.modelo.characteristics.Positionable;
import org.zoo.utilities.ZooPoint;

/**
 * Enumerador que cataloga los tipos de <code>Habitat</code>.
 * @see Habitat
 * @see Sprite
 */
public enum EnumHabitat implements MenuItem {
    MEADOW("Meadow",  MeadowHabitat.class, Sprite.MEADOWHABITAT),
    FOREST("Forest", ForestHabitat.class, Sprite.FORESTHABITAT),
    SAVANNA("Savanna",  SavannaHabitat.class, Sprite.SAVANNAHABITAT),
    SNOWY("Snowy", SnowyHabitat.class, Sprite.SNOWYHABITAT);
    /** El nombre del Habitat. */
    private final String nombre;
    /** La clase del Habitat. */
    private final Class<?> tipo;
    /** El <code>Sprite</code> del <code>Habitat</code> */
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

    /**
     * Crea una instancia del <code>Habitat</code> que corresponde al <code>EnumHabitat</code>
     * @param owner El dueño del Habitat, probablemente un <code>Zoo</code>.
     * @param p La ubicacion relativa del <code>Habitat</code> con respecto al dueño.
     * @return Un <code>Habitat</code> construido con los parametros provistos.
     */
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
