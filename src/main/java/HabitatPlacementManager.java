import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class HabitatPlacementManager extends PlacementManager<EnumHabitat> implements Drawable, MouseMotionListener, MouseListener {
    private Habitat habitat = null;
    private EnumHabitat enumHabitat;
    public HabitatPlacementManager() {
        super();
    }

    @Override
    public void enablePlacement(EnumHabitat enumHabitat) {
        this.enumHabitat = enumHabitat;
        habitat = enumHabitat.newInstance();
        setActivo(true);
    }

    @Override
    public void disablePlacement() {
        enumHabitat = null;
        habitat = null;
        setActivo(false);
    }

    @Override
    public void place() {
        getVistaZoo().addHabitat(getMouseX(), getMouseY(), enumHabitat);
    }


    @Override
    public void draw(Graphics g, int x, int y) {
        if (isActivo()) {
            enumHabitat.getSprite().drawSprite(g, getMouseX(), getMouseY(), habitat.getWidth(), habitat.getHeight(), 0, 0.45f);
            // TODO: Otra manera de hacerlo, pero habría que añadir parámetro de opacidad a draw.
            // habitat.draw(g, mouseX, mouseY);
        }
    }

    // TODO: Arreglar del desastre de los listeners.
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        /*
         * Esto no chequea si está activo el Manager, ya que en ese caso se guardaría la última
         * posición del mouse, y eso es algo raro.
         */
        setMouseX(mouseEvent.getX());
        setMouseY(mouseEvent.getY());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (isActivo()) {
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
