package org.zoo.vista;

import org.zoo.modelo.AnimalPlacementManager;
import org.zoo.modelo.EnumAnimal;
import org.zoo.modelo.EscenaZoo;

import javax.swing.*;

public class PanelAnimal extends JPanel {
    public PanelAnimal(AnimalPlacementManager animalPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        super();
        crearLabelsAnimal(animalPlacementManager, panelListener);
    }
    private void crearLabelsAnimal(AnimalPlacementManager animalPlacementManager, VistaEscenaZoo.PanelListener panelListener) {
        for (EnumAnimal enumAnimal : EnumAnimal.values()) {
            LabelAnimal labelAnimal = new LabelAnimal(100, 100, enumAnimal, animalPlacementManager);
            labelAnimal.addMouseListener(panelListener);
            add(labelAnimal);
        }
    }
}
