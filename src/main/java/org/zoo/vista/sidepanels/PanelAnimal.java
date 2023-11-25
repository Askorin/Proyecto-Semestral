package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;

public class PanelAnimal extends ItemPanel {
    public PanelAnimal(AnimalPlacementManager animalPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        super(panelListener);
        crearLabelsAnimal(animalPlacementManager, panelListener);
        addNavArrow(128, 128, panelListener);
    }
    private void crearLabelsAnimal(AnimalPlacementManager animalPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        for (EnumAnimal enumAnimal : EnumAnimal.values()) {
            LabelAnimal labelAnimal = new LabelAnimal(100, 100, enumAnimal, animalPlacementManager);
            labelAnimal.addMouseListener(panelListener);
            add(labelAnimal);
        }
    }
}
