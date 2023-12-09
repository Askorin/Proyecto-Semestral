package org.zoo.modelo.placementmanager;

import org.zoo.modelo.Zoo;


/**
 * Clase abstracta que define un manejador de posicionamiento de algun objeto
 * generico.
 * @param <T> La clase identificadora para el objeto a posicionar.
 */
abstract public class PlacementManager<T> {
    /**
     * El estado de activacion del <code>PlacementManager</code>, en caso de ser
     * true, se entiende que un objeto esta en capacidades de ser posicionado,
     * y de ser posible se le debe dar feedback visual al usuario.
     */
    private boolean activo;
    private int x, y;
    private Zoo zoo;
    public PlacementManager() {
        activo = false;
    }

    /**
     * Habilita el posicionamiento y lo que conlleva.
     * @param sujeto La instancia identificadora del objeto a ser posicionado.
     */
    public abstract void enablePlacement(T sujeto);

    /**
     * Deshabilita el posicionamiento y lo que conlleva.
     */
    public abstract void disablePlacement();

    /**
     * Posiciona el objeto.
     */
    public abstract void place();
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public boolean isActivo() {
        return activo;
    }

    /**
     * Cambia el estado de activacion del <code>PlacementManager</code>
     * @param activo Un booleano, true si es que se desea activar, false en caso contrario.
     */
    protected void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Zoo getZoo() {
        return zoo;
    }
}

