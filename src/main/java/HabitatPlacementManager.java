import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// TODO: Hacer todo estático es un mal hábito, ver cómo arreglarlo.
public class HabitatPlacementManager implements Drawable, MouseMotionListener, MouseListener {
    // Esto sería innecesario por ahora.
    private static Habitat habitat = null;
    private static EnumHabitat enumHabitat;
    private static boolean activo = false;
    private int x, y;
    private final VistaZoo vistaZoo;
    public HabitatPlacementManager(VistaZoo vistaZoo) {
        this.vistaZoo = vistaZoo;
    }

    public static void enablePlacement(EnumHabitat enumHabitat) {
        HabitatPlacementManager.enumHabitat = enumHabitat;
        HabitatPlacementManager.habitat = enumHabitat.newInstance();
        HabitatPlacementManager.activo = true;
    }

    public void disablePlacement() {
        HabitatPlacementManager.habitat = null;
        HabitatPlacementManager.activo = false;
    }

    public void place() {
        vistaZoo.addHabitat(x, y, enumHabitat);
    }

    public static boolean isActivo() {
        return HabitatPlacementManager.activo;
    }


    public void step() {
    }
    public void draw(Graphics g, int x, int y) {
        if (isActivo()) {
            enumHabitat.getSprite().drawSprite(g, this.x, this.y, habitat.getWidth(), habitat.getHeight(), 0, 0.45f);
            // TODO: Otra manera de hacerlo, pero habría que añadir parámetro de opacidad a draw.
            // habitat.draw(g, this.x, this.y);
        }
    }

    // TODO: Arreglar del desastre de los listeners.
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        /*
         * Esto no chequea si está activo el Manager, ya que en ese caso se guardaría la última
         * posición del mouse, y eso es algo raro.
         */
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (HabitatPlacementManager.isActivo()) {
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
