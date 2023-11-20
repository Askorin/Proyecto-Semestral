import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class EscenaZoo extends JPanel implements Updatable {
    private final VistaZoo zoo;
    private final HabitatPlacementManager habitatPlacementManager;
    private final AnimalPlacementManager animalPlacementManager;
    public EscenaZoo() {
        /* No queremos que swing llame repaint. */
        setIgnoreRepaint(true);

        /* Creamos el Listener de eventos para VistaZoo */
        ZooListener zooListener = new ZooListener();

        /*
         * Resulta necesario entregarle habitatPlacementManager al constructor de VistaZoo.
         * Esto por dos razones:
         * 1- De no ser así, VistaZoo intercepta los eventos (de click y movimiento) que
         * queremos que le lleguen a habitatPlacementManager. Esto podría ser solucionado
         * utilizando keybindings o algún otro sistema de registro de eventos.
         * 2- Para dibujar encima del panel de vistazoo. Si llamaramos draw con los Graphics
         * de EscenaZoo, no se dibujaría correctamente encima de VistaZoo, por lo que
         * resulta necesario utilizar los Graphics de VistaZoo en su paintComponent.
         */
        habitatPlacementManager = new HabitatPlacementManager();
        animalPlacementManager = new AnimalPlacementManager();

        /* Creamos el panel de VistaZoo */
        zoo = new VistaZoo(habitatPlacementManager, animalPlacementManager);
        zoo.addMouseListener(zooListener);
        zoo.addMouseMotionListener(zooListener);

        habitatPlacementManager.setVistaZoo(zoo);
        animalPlacementManager.setVistaZoo(zoo);

        /* Layout, comenzamos a añadir cosas al JPanel. */
        setLayout(new BorderLayout());
        add(zoo, BorderLayout.CENTER);

        /* Paneles. */
        // TODO: Cambiar nombres de PanelAnimal y PanelHabitat, ver si se generalizar a una clase.
        add(new PanelHabitat(habitatPlacementManager), BorderLayout.WEST);
        add(new PanelAnimal(animalPlacementManager), BorderLayout.EAST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void update() {
        zoo.update();
    }

    private class ZooListener implements MouseListener, MouseMotionListener {
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (habitatPlacementManager.isActivo()) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    habitatPlacementManager.place();
                }
                habitatPlacementManager.disablePlacement();
            }

            if (animalPlacementManager.isActivo()) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    animalPlacementManager.place();
                }
                animalPlacementManager.disablePlacement();
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            zoo.setMouseIn(false);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            System.out.println("HOLA!");
            zoo.setMouseIn(true);
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            int mouseX = mouseEvent.getX();
            int mouseY = mouseEvent.getY();

            habitatPlacementManager.setMouseX(mouseX);
            habitatPlacementManager.setMouseY(mouseY);

            animalPlacementManager.setMouseX(mouseX);
            animalPlacementManager.setMouseY(mouseY);

            zoo.setMouseX(mouseX);
            zoo.setMouseY(mouseY);
        }
    }
}
