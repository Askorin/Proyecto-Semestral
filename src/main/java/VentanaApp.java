import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class VentanaApp extends JFrame {
    private int width;
    private int height;
    private VistaZoo zoo;
    public VentanaApp() {
        super("Simulador de Zoológico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ratio 16:9
        width = 850;
        height = 480;
        setSize(width, height);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());


        zoo = new VistaZoo();
        add(zoo, BorderLayout.CENTER);

        // TODO: Cambiar nombres de PanelAnimal y PanelHabitat, ver si se generalizar a una clase.
        add(new PanelHabitat(), BorderLayout.WEST);
        add(new PanelAnimal(), BorderLayout.EAST);

        this.setVisible(true);
    }
    public void step() {
        zoo.step();
    }

}