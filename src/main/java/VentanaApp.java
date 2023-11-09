import javax.swing.*;

public class VentanaApp extends JFrame {
    public VentanaApp() {
        super("Simulador de Zoológico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);

        add(new VistaZoo());
    }
}