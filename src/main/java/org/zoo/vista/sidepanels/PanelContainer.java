package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.awt.*;

public class PanelContainer extends JPanel {
    private JPanel panelAnimal;
    private PanelHabitat panelHabitat;
    private JPanel panelFood;
    private CardLayout cardLayout;

    public PanelContainer(AnimalPlacementManager apm, HabitatPlacementManager hpm, VistaEscenaZoo.PanelListener panelListener) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        panelAnimal = new PanelAnimal(panelListener);
        panelHabitat = new PanelHabitat(panelListener);
        panelFood = new PanelFood(panelListener);

        add(panelHabitat);
        add(panelAnimal);
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
