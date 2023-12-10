package org.zoo.modelo.characteristics;

/**
 * Interfaz cuyos elementos que la implementan son aquellos que
 * tienen coordenadas y se pueden posicionar
 */
public interface Positionable {
    /**
     * Permite obtener las coordenadas globales del objeto
     * @return Coordenada x de la posición absoluta
     */
    int getAbsX();
    /**
     * Permite obtener las coordenadas globales del objeto
     * @return Coordenada y de la posición absoluta
     */
    int getAbsY();
}
