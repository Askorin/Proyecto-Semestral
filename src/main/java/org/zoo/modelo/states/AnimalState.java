package org.zoo.modelo.states;

public interface AnimalState {
    //método que se debe ejecutar INMEDIATAMENTE DESPUES de crear un estado, con el objetivo de separar
    //la creación del objeto estado con las revisiones y procesos que se quieran realizar al principio
    public void stateInit();
    //método que se ejecuta cada step
    public void stateUpdate();
}
