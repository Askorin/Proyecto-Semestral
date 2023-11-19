import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LabelHabitat extends JLabel implements MouseInputListener {

    private EnumHabitat enumHabitat;
    private HabitatPlacementManager habitatPlacementManager;
    LabelHabitat(int width, int height, EnumHabitat enumHabitat, HabitatPlacementManager habitatPlacementManager) {
        super();
        this.enumHabitat = enumHabitat;
        this.habitatPlacementManager = habitatPlacementManager;
        setSize(width, height);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(getClass().getResource(this.enumHabitat.getPath()));
            // imagen = ImageIO.read(getClass().getResource("/habitat.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        Image reImg = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(reImg));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // Habilitamos posicionamiento de habitat.
        habitatPlacementManager.enablePlacement(this.enumHabitat);
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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
