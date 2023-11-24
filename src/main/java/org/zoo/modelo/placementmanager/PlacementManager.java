package org.zoo.modelo.placementmanager;

import org.zoo.modelo.Zoo;

abstract public class PlacementManager<T> {
    private boolean activo;
    private int x, y;
    private Zoo zoo;
    public PlacementManager() {
        activo = false;
    }
    public abstract void enablePlacement(T sujeto);
    public abstract void disablePlacement();
    public abstract void place();
    public void setVistaZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
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

