import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class App {

    private Timer gameLoop;
    // En milisegundos
    private final float FPS = 60;
    public final float MS_PER_FRAME = 1.0f / FPS * 1000;
    private boolean corriendo;
    private VentanaApp frame;

    private final String OS;
    private boolean isLinux;
    private App() {
        // En linux ocurrían problemas de rendimiento, esto lo "arregla".
        OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            isLinux = true;
            System.out.println("Sistema es linux.");
        }
        crearYMostrarUI();
    }
    private void crearYMostrarUI() {
        frame = new VentanaApp();

        this.setupGameLoop();
        this.corriendo = true;
        this.gameLoop.start();
    }

    private void setupGameLoop() {

        gameLoop = new Timer((int) MS_PER_FRAME, (ActionEvent e) -> {
            if (corriendo) {
                frame.step();
                if (isLinux) {
                    Toolkit.getDefaultToolkit().sync();
                }
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}
