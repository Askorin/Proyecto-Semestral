package org.zoo.vista.sidepanels;

import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.awt.*;

public class PanelContainer extends JPanel {
    private final CardLayout cardLayout;

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

    public void switchPanel(LabelNavArrow labelNavArrow) {
        switch (labelNavArrow.getOrientation()) {
            case RIGHT -> cardLayout.next(this);
            case LEFT -> cardLayout.previous(this);
        }
    }
}
