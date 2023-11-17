import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class HabitatPlacementManager implements Drawable, MouseMotionListener {
    private static Habitat habitat = null;
    private int x, y;
    private static boolean activo = false;
    public HabitatPlacementManager() {
    }

    public static void enablePlacement(EnumHabitat enumHabitat) {
        HabitatPlacementManager.habitat = enumHabitat.newInstance();
        HabitatPlacementManager.activo = true;
    }

    public static void disablePlacement() {
        HabitatPlacementManager.habitat = null;
        HabitatPlacementManager.activo = false;
    }

    public static boolean isActivo() {
        return HabitatPlacementManager.activo;
    }


    public void step() {
    }
    public void draw(Graphics g, int x, int y) {
        if (isActivo()) {
            habitat.draw(g, this.x, this.y);
        }
    }

    // TODO: Arreglar del desastre de los listeners.
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (HabitatPlacementManager.activo) {
            this.x = mouseEvent.getX();
            this.y = mouseEvent.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
}
