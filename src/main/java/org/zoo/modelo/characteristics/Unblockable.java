package org.zoo.modelo.characteristics;

import org.zoo.utilities.Hitbox;

/**
 * Interfaz cuyos elementos que la implementan son aquellos que
 * impiden que otros objetos se sobrepongan a ellos
 */
public interface Unblockable {
    /**
     * Permite obtener la hitbox del objeto relativa al contenedor mas inmediato que lo contenga
     * @return Hitbox con posicion relativa y tamaño
     */
    Hitbox getHitbox();

    /**
     * Permite obtener la hitbox absoluta del objeto, es decir, en relacion al zoo
     * @return Hitbox con posicion absoluta y tamaño
     */
    Hitbox getAbsHitbox();
}

