package org.zoo.modelo.states;

import org.zoo.modelo.exception.AlreadyInitializedStateException;
import org.zoo.modelo.exception.NotInitializedStateException;

/**
 * Interfaz que implementan las clases que describen el comportamiento
 * de <code>Animal</code> en un determinado estado
 */
public interface AnimalState {
    //método que se debe ejecutar INMEDIATAMENTE DESPUES de crear un estado, con el objetivo de separar
    //la creación del objeto estado con las revisiones y procesos que se quieran realizar al principio

    /**
     * Proceso de inicialización del estado.
     * Se debe ejecutar inmediatamente despues de crear la instancia del estado
     * @throws AlreadyInitializedStateException En caso de ya haberse llamado este metodo
     */
    public void stateInit() throws AlreadyInitializedStateException;

    /**
     * Describe el comportamiento de <code>Animal</code> propio del estado
     * que se ejecuta en cada frame logico del programa
     * @throws NotInitializedStateException En caso de no haber llamado antes <code>stateinit()</code>
     */
    public void stateUpdate() throws NotInitializedStateException;
}
