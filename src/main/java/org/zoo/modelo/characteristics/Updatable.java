package org.zoo.modelo.characteristics;

/**
 * Interfaz que la implementan aquellos elementos del modelo
 * que deben actualizarse una vez por cada frame del programa
 */
public interface Updatable {
    /**
     * Metodo que se debe llamar una y solo una vez cada frame logico del programa
     */
    void update();
}
