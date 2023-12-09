package org.zoo.vista.sidepanels;

import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.awt.*;


/**
 * Contenedor de paneles de interaccion del zoo.
 */
public class PanelContainer extends JPanel {

    /**
     * El layout del panel, corresponde a un CardLayout que puede ser navegado para
     * acceder a los distintos paneles.
     */
    private final CardLayout cardLayout;

    /**
     * Constructor unico de PanelContainer.
     * @param panelListener La referencia al listener de eventos para paneles.
     */
    public PanelContainer(VistaEscenaZoo.PanelListener panelListener) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);


        ItemPanel<EnumAnimal> panelAnimal = new ItemPanel<>(panelListener, EnumAnimal.class);
        ItemPanel<EnumHabitat> panelHabitat = new ItemPanel<>(panelListener, EnumHabitat.class);
        ItemPanel<EnumFood> panelFood = new ItemPanel<>(panelListener, EnumFood.class);

        add(panelAnimal);
        add(panelHabitat);
        add(panelFood);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Funcion que controla el cambio de paneles dentro del contenedor.
     * @param orientation La orientacion en la que debe cambiar el layout.
     */
    public void switchPanel(LabelNavArrow.NavArrowOrientation orientation) {
        switch (orientation) {
            case RIGHT -> cardLayout.next(this);
            case LEFT -> cardLayout.previous(this);
        }
    }
}
