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

        // transitionPanel = new TransitionPanel();
        panelAnimal = new PanelAnimal(apm, panelListener);
        panelHabitat = new PanelHabitat(hpm, panelListener);

        // add(transitionPanel);
        add(panelAnimal);
        add(panelHabitat);

        cardLayout.next(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void nextPanel() {
        cardLayout.next(this);
    }
}
