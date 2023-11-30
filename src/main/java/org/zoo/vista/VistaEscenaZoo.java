package org.zoo.vista;
import org.zoo.modelo.EscenaZoo;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.sidepanels.*;
import org.zoo.vista.visitor.DrawVisitor;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class VistaEscenaZoo extends JPanel {
    private final DrawVisitor renderZoo;
    private final EscenaZoo escenaZoo;
    private final PanelContainer panelContainer;
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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(renderZoo);

        /* Paneles. */
        AnimalPlacementManager apm = escenaZoo.getAnimalPlacementManager();
        HabitatPlacementManager hpm = escenaZoo.getHabitatPlacementManager();
        panelContainer = new PanelContainer(apm, hpm, panelListener);
        add(panelContainer);

        // PanelHabitat panelHabitat = new PanelHabitat(escenaZoo.getHabitatPlacementManager(), panelListener);
        // PanelAnimal panelAnimal = new PanelAnimal(escenaZoo.getAnimalPlacementManager(), panelListener);
        // panelHabitat.addMouseListener(panelListener);
        // panelAnimal.addMouseListener(panelListener);

        // TODO: Cambiar nombres de org.zoo.vista.sidepanels.PanelAnimal y org.zoo.vista.sidepanels.PanelHabitat, ver si se generalizar a una clase.
        // add(panelHabitat);
        // add(panelAnimal, BorderLayout.EAST);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public class ZooListener implements MouseInputListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            renderZoo.setDragging(true);
            /* Esto es necesario para porder actualizar posición del mouse */
            mouseMoved(mouseEvent);
        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            /*
             * Esto es para permitirle al usuario arrastrar la camara
             * mientras posiciona elementos en el zoo.
             */
            if (renderZoo.isDragging()) {
                renderZoo.setDragging(false);
                return;
            }
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

            if (escenaZoo.getFoodPlacementManager().isActivo()) {
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    escenaZoo.getFoodPlacementManager().place();
                }
                escenaZoo.getFoodPlacementManager().disablePlacement();
            }
        }


        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            /*
             * RenderZoo actualiza posición del cursero para uso con camara solo
             * cuando no se esta arrastrando la camara.
             */
            renderZoo.setMouseX(mouseEvent.getX());
            renderZoo.setMouseY(mouseEvent.getY());


            /* Acá se actualiza la posición de los placement managers. */
            int placementX = mouseEvent.getX() + renderZoo.getCameraX();
            int placementY = mouseEvent.getY() + renderZoo.getCameraY();

            escenaZoo.getHabitatPlacementManager().setX(placementX);
            escenaZoo.getHabitatPlacementManager().setY(placementY);

            escenaZoo.getAnimalPlacementManager().setX(placementX);
            escenaZoo.getAnimalPlacementManager().setY(placementY);

            escenaZoo.getFoodPlacementManager().setX(placementX);
            escenaZoo.getFoodPlacementManager().setY(placementY);
        }
    }
    public class PanelListener implements MouseInputListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            escenaZoo.getHabitatPlacementManager().disablePlacement();
            escenaZoo.getAnimalPlacementManager().disablePlacement();
            escenaZoo.getFoodPlacementManager().disablePlacement();

            Component source = mouseEvent.getComponent();
            // TODO: Esto se puede generalizar
            if (source instanceof LabelHabitat label) {
                escenaZoo.getHabitatPlacementManager().enablePlacement(label.getEnumHabitat());
            } else if (source instanceof LabelAnimal label) {
                escenaZoo.getAnimalPlacementManager().enablePlacement(label.getEnumAnimal());
            } else if (source instanceof LabelFood label) {
                escenaZoo.getFoodPlacementManager().enablePlacement(label.getEnumFood());
            } else if (source instanceof LabelNavArrow label) {
                panelContainer.switchPanel(label);
            }
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
