package org.zoo;

import org.zoo.vista.DrawVisitor;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;

public class EscenaZoo extends JPanel implements Updatable {
    private final Zoo zoo;
    private final DrawVisitor renderZoo;
    private final HabitatPlacementManager habitatPlacementManager;
    private final AnimalPlacementManager animalPlacementManager;
    public EscenaZoo() {
        /* No queremos que swing llame repaint. */
        setIgnoreRepaint(true);

        /* Creamos los listeners para eventos */
        ZooListener zooListener = new ZooListener();
        PanelListener panelListener = new PanelListener();

        /*
         * Resulta necesario entregarle habitatPlacementManager al constructor de org.zoo.VistaZoo.
         * Esto por dos razones:
         * 1- De no ser así, org.zoo.Zoo intercepta los eventos (de click y movimiento) que
         * queremos que le lleguen a habitatPlacementManager. Esto podría ser solucionado
         * utilizando keybindings o algún otro sistema de registro de eventos.
         * 2- Para dibujar encima del panel de vistazoo. Si llamaramos draw con los Graphics
         * de org.zoo.EscenaZoo, no se dibujaría correctamente encima de org.zoo.Zoo, por lo que
         * resulta necesario utilizar los Graphics de org.zoo.Zoo en su paintComponent.
         */
        habitatPlacementManager = new HabitatPlacementManager();
        animalPlacementManager = new AnimalPlacementManager();

        /* Creamos la logica de org.zoo.Zoo */
        zoo = new Zoo(habitatPlacementManager, animalPlacementManager);

        habitatPlacementManager.setVistaZoo(zoo);
        animalPlacementManager.setVistaZoo(zoo);

        /* Creamos la vista de org.zoo.Zoo */
        renderZoo = new DrawVisitor(zoo);
        renderZoo.addMouseListener(zooListener);
        renderZoo.addMouseMotionListener(zooListener);

        /* Layout, comenzamos a añadir cosas al JPanel. */
        setLayout(new BorderLayout());
        add(renderZoo, BorderLayout.CENTER);

        /* Paneles. */
        PanelHabitat panelHabitat = new PanelHabitat(habitatPlacementManager, panelListener);
        PanelAnimal panelAnimal = new PanelAnimal(animalPlacementManager, panelListener);
        panelHabitat.addMouseListener(panelListener);
        panelAnimal.addMouseListener(panelListener);

        // TODO: Cambiar nombres de org.zoo.PanelAnimal y org.zoo.PanelHabitat, ver si se generalizar a una clase.
        add(panelHabitat, BorderLayout.WEST);
        add(panelAnimal, BorderLayout.EAST);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void update() {
        zoo.update();
    }

    public class ZooListener implements MouseInputListener, MouseMotionListener {
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
            renderZoo.setMouseIn(false);
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            renderZoo.setMouseIn(true);
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

            renderZoo.setMouseX(mouseX);
            renderZoo.setMouseY(mouseY);
        }
    }
    public class PanelListener implements MouseInputListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Component source = mouseEvent.getComponent();
            if (source instanceof LabelHabitat label) {
                habitatPlacementManager.enablePlacement(label.getEnumHabitat());
            } else if (source instanceof LabelAnimal label) {
                animalPlacementManager.enablePlacement(label.getEnumAnimal());
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

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }
}
