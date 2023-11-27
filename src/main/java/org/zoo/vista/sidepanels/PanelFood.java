package org.zoo.vista.sidepanels;

import org.zoo.modelo.food.EnumFood;
import org.zoo.modelo.habitat.EnumHabitat;
import org.zoo.modelo.placementmanager.HabitatPlacementManager;
import org.zoo.vista.VistaEscenaZoo;

public class PanelFood extends ItemPanel {
    public PanelFood(VistaEscenaZoo.PanelListener panelListener) {
        super(panelListener);
        addNavArrowL(128, 128, panelListener);
        crearLabelsFood(panelListener);
        addNavArrowR(128, 128, panelListener);
    }
    private void crearLabelsFood(VistaEscenaZoo.PanelListener panelListener) {
        for (EnumFood enumFood : EnumFood.values()) {
            LabelFood labelFood = new LabelFood(100, 100, enumFood);
            labelFood.addMouseListener(panelListener);
            add(labelFood);
        }
    }
}
