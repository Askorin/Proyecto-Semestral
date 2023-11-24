package org.zoo.modelo;

// TODO: Hacer que agarre de cosas se produzca al centro, no en la esquina.
abstract public class PlacementManager<T> {
    private boolean activo;
    private int mouseX, mouseY;
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

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public Zoo getZoo() {
        return zoo;
    }
}

