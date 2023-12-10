package org.zoo.modelo.states;

/**
 * Interfaz que implementan las clases que describen el comportamiento
 * de <code>Animal</code> en una determinado estado
 */
public interface AnimalState {
    //método que se debe ejecutar INMEDIATAMENTE DESPUES de crear un estado, con el objetivo de separar
    //la creación del objeto estado con las revisiones y procesos que se quieran realizar al principio

    /**
     * Proceso de inicialización del estado.
     * Se debe ejecutar inmediatamente despues de crear la instancia del estado
     */
    public void stateInit();

    /**
     * Describe el comportamiento de <code>Animal</code> propio del estado
     * que se ejecuta en cada frame logico del programa
     */
    public void stateUpdate();
}
