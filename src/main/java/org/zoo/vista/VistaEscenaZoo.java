package org.zoo.vista;
import org.zoo.modelo.EscenaZoo;
import org.zoo.vista.sidepanels.LabelAnimal;
import org.zoo.vista.sidepanels.LabelHabitat;
import org.zoo.vista.sidepanels.PanelAnimal;
import org.zoo.vista.sidepanels.PanelHabitat;
import org.zoo.vista.visitor.DrawVisitor;

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

        // TODO: Cambiar nombres de org.zoo.vista.sidepanels.PanelAnimal y org.zoo.vista.sidepanels.PanelHabitat, ver si se generalizar a una clase.
        add(panelHabitat, BorderLayout.WEST);
        add(panelAnimal, BorderLayout.EAST);

    }
    @Override
    protected void paintComponent(Graphics g) {
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
            // TODO: Acá se deberia setear un X distinto para hpm y apm!
            // relativo a camara.

            // RenderZoo actualiza posición del cursero para uso con camara.
            renderZoo.setMouseX(mouseEvent.getX());
            renderZoo.setMouseY(mouseEvent.getY());


            // Acá actualizamos la posición de los placement managers.
            int placementX = mouseEvent.getX() + renderZoo.getCameraX();
            int placementY = mouseEvent.getY() + renderZoo.getCameraY();

            escenaZoo.getHabitatPlacementManager().setX(placementX);
            escenaZoo.getHabitatPlacementManager().setY(placementY);

            escenaZoo.getAnimalPlacementManager().setX(placementX);
            escenaZoo.getAnimalPlacementManager().setY(placementY);

            // System.out.println("MouseX: " + mouseX);
            // System.out.println("MouseX Placement: " + escenaZoo.getHabitatPlacementManager().getX());
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
