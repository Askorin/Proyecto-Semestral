package org.zoo;

import org.zoo.modelo.EscenaZoo;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Clase principal de la aplicación.
 */

public final class App {

    /** Timer del gameLoop, corre cada cierto tiempo fijo. */
    private Timer gameLoop;
    private final float FPS = 60;
    public final float MS_PER_FRAME = 1.0f / FPS * 1000;
    private boolean corriendo;
    private JFrame frame;
    private EscenaZoo escenaZoo;
    private VistaEscenaZoo vistaEscenaZoo;
    private final String OS;
    private boolean isLinux;
    public static boolean SEE_HITBOX = false;

    /**
     * Único constructor de la aplicación, crea e inicializa un timer que contiene
     * el gameloop del juego.
     */
    private App() {
        // En linux ocurrían problemas de rendimiento, esto lo "arregla".
        OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            isLinux = true;
            System.out.println("Sistema es linux.");
        }
        crearYMostrarUI();
    }

    /**
     * Método que crea la escena, la vista e inicializa el gameloop.
     */
    private void crearYMostrarUI() {
        escenaZoo = new EscenaZoo();
        vistaEscenaZoo = new VistaEscenaZoo(escenaZoo);

        this.setupGameLoop();

        frame = new JFrame("Zoo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ratio 16:9
        int width = 850;
        int height = 480;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(vistaEscenaZoo);
        frame.setVisible(true);


        this.corriendo = true;
        this.gameLoop.start();
    }

    /**
     * Método que define el gameloop a correr.
     */
    private void setupGameLoop() {

        gameLoop = new Timer((int) MS_PER_FRAME, (ActionEvent e) -> {
            if (corriendo) {
                escenaZoo.update();
                if (isLinux) {
                    Toolkit.getDefaultToolkit().sync();
                }
                vistaEscenaZoo.repaint();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String hitboxParam = args[0];
            App.SEE_HITBOX = (Integer.parseInt(args[0]) != 0);
        }
        SwingUtilities.invokeLater(App::new);
    }
}
