package org.zoo;

import javax.swing.*;

public class PanelAnimal extends JPanel {
    PanelAnimal(AnimalPlacementManager animalPlacementManager, EscenaZoo.PanelListener panelListener) {
        super();
        crearLabelsAnimal(animalPlacementManager, panelListener);
    }
    private void crearLabelsAnimal(AnimalPlacementManager animalPlacementManager, EscenaZoo.PanelListener panelListener) {
        for (EnumAnimal enumAnimal : EnumAnimal.values()) {
            LabelAnimal labelAnimal = new LabelAnimal(100, 100, enumAnimal, animalPlacementManager);
            labelAnimal.addMouseListener(panelListener);
            add(labelAnimal);
        }
    }
}
