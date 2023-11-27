package org.zoo.vista.sidepanels;

import org.zoo.modelo.placementmanager.AnimalPlacementManager;
import org.zoo.modelo.animal.EnumAnimal;
import org.zoo.vista.VistaEscenaZoo;

import javax.swing.*;

public class PanelAnimal extends ItemPanel {
    public PanelAnimal(VistaEscenaZoo.PanelListener panelListener) {
        super(panelListener);
        addNavArrowL(128, 128, panelListener);
        crearLabelsAnimal(panelListener);
        addNavArrowR(128, 128, panelListener);
    }
    private void crearLabelsAnimal(VistaEscenaZoo.PanelListener panelListener) {
        for (EnumAnimal enumAnimal : EnumAnimal.values()) {
            LabelAnimal labelAnimal = new LabelAnimal(100, 100, enumAnimal);
            labelAnimal.addMouseListener(panelListener);
            add(labelAnimal);
        }
    }
}
