package org.zoo.vista;
import org.zoo.modelo.EscenaZoo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class VistaEscenaZoo extends JPanel {
    private final DrawVisitor renderZoo;
    private final EscenaZoo escenaZoo;
    public VistaEscenaZoo(EscenaZoo escenaZoo) {
        /* No queremos que swing llame repaint. */
        setIgnoreRepaint(true);

        /* Creamos la EscenaZoo, que controlará la parte lógica del simulador. */
        this.escenaZoo = escenaZoo;

        /* Creamos los listeners para eventos */
        ZooListener zooListener = new ZooListener();
        PanelListener panelListener = new PanelListener();

        /* Creamos la vista de org.zoo.modelo.Zoo */
        renderZoo = new DrawVisitor(escenaZoo.getZoo());
        renderZoo.addMouseListener(zooListener);
        renderZoo.addMouseMotionListener(zooListener);

        /* Layout, comenzamos a añadir cosas al JPanel. */
        setLayout(new BorderLayout());
        add(renderZoo, BorderLayout.CENTER);

        /* Paneles. */
        PanelHabitat panelHabitat = new PanelHabitat(escenaZoo.getHabitatPlacementManager(), panelListener);
        PanelAnimal panelAnimal = new PanelAnimal(escenaZoo.getAnimalPlacementManager(), panelListener);
        panelHabitat.addMouseListener(panelListener);
        panelAnimal.addMouseListener(panelListener);

        // TODO: Cambiar nombres de org.zoo.vista.PanelAnimal y org.zoo.vista.PanelHabitat, ver si se generalizar a una clase.
        add(panelHabitat, BorderLayout.WEST);
        add(panelAnimal, BorderLayout.EAST);

    }
    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Hola!");
        super.paintComponent(g);
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
            if (escenaZoo.getHabitatPlacementManager().isActivo()) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    escenaZoo.getHabitatPlacementManager().place();
                }
                escenaZoo.getHabitatPlacementManager().disablePlacement();
            }

            if (escenaZoo.getAnimalPlacementManager().isActivo()) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    escenaZoo.getAnimalPlacementManager().place();
                }
                escenaZoo.getAnimalPlacementManager().disablePlacement();
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

            escenaZoo.getHabitatPlacementManager().setMouseX(mouseX);
            escenaZoo.getHabitatPlacementManager().setMouseY(mouseY);

            escenaZoo.getAnimalPlacementManager().setMouseX(mouseX);
            escenaZoo.getAnimalPlacementManager().setMouseY(mouseY);

            renderZoo.setMouseX(mouseX);
            renderZoo.setMouseY(mouseY);
        }
    }
    public class PanelListener implements MouseInputListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Component source = mouseEvent.getComponent();
            if (source instanceof LabelHabitat label) {
                escenaZoo.getHabitatPlacementManager().enablePlacement(label.getEnumHabitat());
            } else if (source instanceof LabelAnimal label) {
                escenaZoo.getAnimalPlacementManager().enablePlacement(label.getEnumAnimal());
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
