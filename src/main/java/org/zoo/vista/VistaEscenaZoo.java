package org.zoo.vista;
import org.zoo.modelo.EscenaZoo;
import org.zoo.modelo.placementmanager.PlacementManager;
import org.zoo.vista.sidepanels.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Contraparte grafica de la escena del zoologico.
 */
public class VistaEscenaZoo extends JPanel {

    /** Encargado de renderizar la escena del zoologico. */
    private final DrawVisitor renderZoo;

    /** Parte logica del simulador. */
    private final EscenaZoo escenaZoo;

    /** Contenedor de paneles interactivos. */
    private final PanelContainer panelContainer;

    /**
     * Constructor único de la clase VistaEscenaZoo
     * @param escenaZoo La instancia de EscenaZoo que controla la lógica.
     */
    public VistaEscenaZoo(EscenaZoo escenaZoo) {
        /* No queremos que swing llame repaint. */
        setIgnoreRepaint(true);

        /* Creamos la EscenaZoo, que controlará la parte lógica del simulador. */
        this.escenaZoo = escenaZoo;

        /* Creamos los listeners para eventos */
        ZooListener zooListener = new ZooListener();
        PanelListener panelListener = new PanelListener();

        /* Creamos la vista de org.zoo.modelo.Zoo */
        renderZoo = new DrawVisitor(escenaZoo);
        renderZoo.addMouseListener(zooListener);
        renderZoo.addMouseMotionListener(zooListener);

        /* Layout, comenzamos a añadir cosas al JPanel. */
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(renderZoo);

        /* Paneles. */
        panelContainer = new PanelContainer(panelListener);
        add(panelContainer);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Clase interna VistaEscenaZoo, se encarga del manejo de inputs del usuario dentro
     * del area del zoologico.
     */
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

    /**
     * Clase interna de VistaEscenaZoo, se encarga de manejar los inputs del usuario
     * dentro de los paneles interactivos.
     */
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

            if (source instanceof ItemLabel<?> itemLabel) {
                PlacementManager pm = escenaZoo.getPlacementManager(itemLabel.getEnum());
                if (pm != null) pm.enablePlacement(itemLabel.getEnum());
            } else if (source instanceof LabelNavArrow arrowLabel) {
                panelContainer.switchPanel(arrowLabel.getOrientation());
            }
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            Component source = mouseEvent.getComponent();

            if (source instanceof HoverVisuals hoverable) {
                hoverable.setHoverState(true);
            }

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            Component source = mouseEvent.getComponent();

            if (source instanceof HoverVisuals hoverable) {
                hoverable.setHoverState(false);
            }

        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {

        }
    }
}
