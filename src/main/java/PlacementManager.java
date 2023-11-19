// TODO: Hacer que agarre de cosas se produzca al centro, no en la esquina.
abstract public class PlacementManager<T> {
    private boolean activo;
    private int mouseX, mouseY;
    private VistaZoo vistaZoo;
    public PlacementManager() {
        activo = false;
    }
    public abstract void enablePlacement(T sujeto);
    public abstract void disablePlacement();
    public abstract void place();
    public void setVistaZoo(VistaZoo vistaZoo) {
        this.vistaZoo = vistaZoo;
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

    public VistaZoo getVistaZoo() {
        return vistaZoo;
    }
}
