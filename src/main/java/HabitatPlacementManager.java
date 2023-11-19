import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class HabitatPlacementManager implements Drawable, MouseMotionListener, MouseListener {
    // Esto sería innecesario por ahora.
    private Habitat habitat = null;
    private EnumHabitat enumHabitat;
    private boolean activo;
    private int mouseX, mouseY;
    private VistaZoo vistaZoo;
    public HabitatPlacementManager() {
        activo = false;
    }

    public void enablePlacement(EnumHabitat enumHabitat) {
        this.enumHabitat = enumHabitat;
        habitat = enumHabitat.newInstance();
        activo = true;
    }

    public void setVistaZoo(VistaZoo vistaZoo) {
        this.vistaZoo = vistaZoo;
    }

    public void disablePlacement() {
        enumHabitat = null;
        habitat = null;
        activo = false;
    }

    public boolean isActivo() {
        return activo;
    }

    public void place() {
        vistaZoo.addHabitat(mouseX, mouseY, enumHabitat);
    }


    public void step() {
    }
    public void draw(Graphics g, int x, int y) {
        if (activo) {
            // enumHabitat.getSprite().drawSprite(g, mouseX, mouseY, habitat.getWidth(), habitat.getHeight(), 0, 0.45f);
            // TODO: Otra manera de hacerlo, pero habría que añadir parámetro de opacidad a draw.
            habitat.draw(g, mouseX, mouseY);
        }
    }

    // TODO: Arreglar del desastre de los listeners.
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        /*
         * Esto no chequea si está activo el Manager, ya que en ese caso se guardaría la última
         * posición del mouse, y eso es algo raro.
         */
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (activo) {
            if (mouseEvent.getButton() == MouseEvent.BUTTON1) {place();}
            disablePlacement();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
