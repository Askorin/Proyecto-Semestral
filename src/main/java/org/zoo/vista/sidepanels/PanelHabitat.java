package org.zoo.vista.sidepanels;

import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;
import java.util.ArrayList;

public class PanelHabitat extends JPanel {

    private ArrayList<LabelHabitat> labels;
    public PanelHabitat(HabitatPlacementManager habitatPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        super();
        crearLabelsHabitat(habitatPlacementManager, panelListener);
    }

    private void crearLabelsHabitat(HabitatPlacementManager habitatPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        for (EnumHabitat enumHabitat : EnumHabitat.values()) {
            LabelHabitat labelHabitat = new LabelHabitat(100, 100, enumHabitat, habitatPlacementManager);
            labelHabitat.addMouseListener(panelListener);
            add(labelHabitat);
        }
    }
}
